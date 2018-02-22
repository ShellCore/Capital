package com.shellcore.android.capital.ui.accounts.ui

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shellcore.android.capital.CapitalApplication
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.ui.account.ui.AccountDetailActivity
import com.shellcore.android.capital.ui.accounts.AccountsPresenter
import com.shellcore.android.capital.ui.accounts.ui.adapter.AccountListener
import com.shellcore.android.capital.ui.accounts.ui.adapter.AccountsAdapter
import com.shellcore.android.capital.utils.showMessage
import kotlinx.android.synthetic.main.fragment_accounts.*
import javax.inject.Inject

class AccountsFragment : Fragment(), AccountsView, AccountListener {

    companion object {
        const val CREATE_ACCOUNT = 1
        const val UPDATE_ACCOUNT = 2
    }

    private lateinit var snackbar: Snackbar
    private lateinit var accountsAdapter: AccountsAdapter

    @Inject
    lateinit var presenter: AccountsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponents()
        setupInjection()
        setupAdapter()
        setupList()

        presenter.onCreate()
        presenter.getAccounts()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 0) {
            presenter.getAccounts()
        }
    }

    override fun showProgress() {
        snackbar.show()
    }

    override fun hideProgress() {
        snackbar.dismiss()
    }

    override fun showMessage(message: String) {
        contenAccounts.showMessage(message)
    }

    override fun showList() {
        crdAccounts.visibility = View.VISIBLE
        txtEmptyList.visibility = View.GONE
    }

    override fun hideList() {
        crdAccounts.visibility = View.GONE
        txtEmptyList.visibility = View.VISIBLE
    }

    override fun updateList(accounts: List<Account>) {
        accountsAdapter.updateAccounts(accounts)
    }

    override fun onClick(account: Account) {
        val intent = Intent(activity, AccountDetailActivity::class.java).apply {
            putExtra(AccountDetailActivity.ACCOUNT_KEY, account)
        }
        startActivityForResult(intent, UPDATE_ACCOUNT)
    }

    private fun setupComponents() {
        snackbar = Snackbar.make(contenAccounts, R.string.default_progress_wait, Snackbar.LENGTH_INDEFINITE)
        fltAddAccount.setOnClickListener {
            addAccount()
        }
    }

    private fun setupInjection() {
        val app = activity.application as CapitalApplication
        val component = app.getAccountsComponent(activity, this)
        component.inject(this)
    }

    private fun setupAdapter() {
        accountsAdapter = AccountsAdapter(listOf(), this)
    }

    private fun setupList() {
        recAccounts.apply {
            layoutManager = LinearLayoutManager(this.context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            this.adapter = accountsAdapter
        }
    }

    private fun addAccount() {
        startActivityForResult(Intent(activity, AccountDetailActivity::class.java), CREATE_ACCOUNT)
    }
}
