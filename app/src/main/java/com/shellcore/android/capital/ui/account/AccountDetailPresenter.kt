package com.shellcore.android.capital.ui.account

import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.ui.account.events.AccountDetailEvent

interface AccountDetailPresenter {
    fun onCreate()
    fun onDestroy()
    fun saveAccount(account: Account)

    fun onEventMainThread(event: AccountDetailEvent)
}