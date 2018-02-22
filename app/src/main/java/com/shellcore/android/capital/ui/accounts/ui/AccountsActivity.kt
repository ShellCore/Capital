package com.shellcore.android.capital.ui.accounts.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toolbar
import com.shellcore.android.capital.CapitalApplication
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.ui.account.ui.AccountDetailActivity
import com.shellcore.android.capital.ui.accounts.AccountsPresenter
import com.shellcore.android.capital.ui.accounts.ui.adapter.AccountListener
import com.shellcore.android.capital.ui.accounts.ui.adapter.AccountsAdapter
import com.shellcore.android.capital.ui.base.ToolbarActivity
import com.shellcore.android.capital.utils.showMessage
import kotlinx.android.synthetic.main.activity_accounts.*
import javax.inject.Inject

/**
 * Created by MOGC. 2018/02/20.
 */
class AccountsActivity : ToolbarActivity(), AccountsView, AccountListener {

    companion object {
        const val CREATE_ACCOUNT = 1
        const val UPDATE_ACCOUNT = 2
    }

    private lateinit var snackbar: Snackbar
    private lateinit var accountsAdapter: AccountsAdapter

    @Inject
    lateinit var presenter: AccountsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts)
        setupToolbar(toolbar as Toolbar)
        setupComponents()
        setupInjection()
        setupAdapter()
        setupList()

        presenter.onCreate()
        presenter.getAccounts()

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
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
        val intent = Intent(this, AccountDetailActivity::class.java).apply {
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
        val app = application as CapitalApplication
        val component = app.getAccountsComponent(this, this)
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
        startActivityForResult(Intent(this, AccountDetailActivity::class.java), CREATE_ACCOUNT)
    }
}
