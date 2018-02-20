package com.shellcore.android.capital.ui.accounts

import com.shellcore.android.capital.ui.accounts.events.AccountsEvent

/**
 * Created by MOGC. 2018/02/20.
 */
interface AccountsPresenter {

    fun onCreate()
    fun onDestroy()

    fun getAccounts()

    fun onEventMainThread(event: AccountsEvent)
}

