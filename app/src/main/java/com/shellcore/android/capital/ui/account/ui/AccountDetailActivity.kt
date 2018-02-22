package com.shellcore.android.capital.ui.account.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.shellcore.android.capital.CapitalApplication
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.ui.account.AccountDetailPresenter
import com.shellcore.android.capital.ui.base.ToolbarActivity
import com.shellcore.android.capital.utils.showMessage
import kotlinx.android.synthetic.main.activity_account_detail.*
import javax.inject.Inject

/**
 * Created by MOGC. 2018/02/21.
 */
class AccountDetailActivity : ToolbarActivity(), AccountDetailView, AdapterView.OnItemSelectedListener {

    companion object {
        const val ACCOUNT_KEY = "account"
    }

    @Inject
    lateinit var presenter: AccountDetailPresenter

    private lateinit var account: Account
    private lateinit var snackbar: Snackbar
    private lateinit var accountTypeList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_detail)
        setupToolbar(toolbar as Toolbar)
        setupComponents()
        setupInjection()
        getAccount()

        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        snackbar.show()
    }

    override fun hideProgress() {
        snackbar.dismiss()
    }

    override fun enableInputs() {
        setInputsEnabled(true)
    }

    override fun disableInputs() {
        setInputsEnabled(false)
    }

    override fun showMessage(message: String) {
        cnsAccountDetail.showMessage(message)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        account.type = position
    }

    override fun returnToAccountList(resultCode: Int) {
        setResult(resultCode)
        finish()
    }

    private fun setInputsEnabled(enabled: Boolean) {
        tilName.editText!!.isEnabled = enabled
        tilLimit.editText!!.isEnabled = enabled
        btnCancel.isEnabled = enabled
        btnAccept.isEnabled = enabled
    }

    private fun setupComponents() {
        snackbar = Snackbar.make(cnsAccountDetail, R.string.default_progress_wait, Snackbar.LENGTH_SHORT)

        accountTypeList = resources.getStringArray(R.array.accounts_types)
        spnAccountType.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountTypeList)
        spnAccountType.onItemSelectedListener = this

        btnCancel.setOnClickListener {
            returnToAccountList(-1)
        }
        btnAccept.setOnClickListener { saveAccount() }
    }

    private fun setupInjection() {
        val app = application as CapitalApplication
        val component = app.getAccountDetailComponent(this, this)
        component.inject(this)
    }

    private fun getAccount() {
        if (intent.hasExtra(ACCOUNT_KEY)) {
            account = intent.getParcelableExtra(ACCOUNT_KEY)
            tilName.editText!!.setText(account.name)
            tilLimit.editText!!.setText(account.limit.toString())
            spnAccountType.setSelection(account.type)
        } else {
            account = Account()
        }
    }

    private fun saveAccount() {

        account.apply {

            name = tilName.editText!!.text.toString()
            balance = 0.0
            if (type == Account.CREDIT) {
                limit = tilLimit.editText!!.text.toString().toDouble()
            }
            presenter.saveAccount(this)
        }
    }
}
