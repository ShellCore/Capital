package com.shellcore.android.capital.ui.account.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toolbar
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
class AccountDetailActivity : ToolbarActivity(), AccountDetailView {

    companion object {
        const val ACCOUNT_KEY = "account"
    }

    private lateinit var account: Account
    private lateinit var snackbar: Snackbar

    @Inject
    lateinit var presenter: AccountDetailPresenter

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

    private fun setInputsEnabled(enabled: Boolean) {
        tilName.editText!!.isEnabled = enabled
        tilLimit.editText!!.isEnabled = enabled
        btnCancel.isEnabled = enabled
        btnAccept.isEnabled = enabled
    }

    private fun setupComponents() {
        snackbar = Snackbar.make(cnsAccountDetail, R.string.progress_wait, Snackbar.LENGTH_SHORT)
        btnCancel.setOnClickListener { onBackPressed() }
        btnAccept.setOnClickListener { saveAccount() }
    }

    private fun setupInjection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getAccount() {
        if (intent.hasExtra(ACCOUNT_KEY)) {
            account = intent.getParcelableExtra<Account>(ACCOUNT_KEY)
        } else {
            account = Account()
        }
    }

    private fun saveAccount() {

        account.apply {

            name = tilName.editText!!.text.toString()
            // type = //TODO Falta realizar la lista de tipos de cuenta
            balance = 0.0
            if (type == Account.CREDIT) {
                limit = tilLimit.editText!!.text.toString() as Double
            }
            presenter.saveAccount(this)
        }
    }
}
