package com.shellcore.android.capital.ui.accounts.ui

import com.shellcore.android.capital.db.models.Account

/**
 * Created by MOGC. 2018/02/20.
 */
interface AccountsView {

    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)

    fun showList()
    fun hideList()
    fun updateList(accounts: List<Account>)
}