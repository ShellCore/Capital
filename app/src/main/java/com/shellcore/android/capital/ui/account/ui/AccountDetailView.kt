package com.shellcore.android.capital.ui.account.ui

/**
 * Created by MOGC. 2018/02/21.
 */
interface AccountDetailView {

    fun showProgress()
    fun hideProgress()
    fun enableInputs()
    fun disableInputs()
    fun showMessage(message: String)
    fun returnToAccountList(resultCode: Int)
}