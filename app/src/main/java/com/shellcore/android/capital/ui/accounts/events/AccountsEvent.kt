package com.shellcore.android.capital.ui.accounts.events

import com.shellcore.android.capital.base.BaseEvent
import com.shellcore.android.capital.db.models.Account

/**
 * Created by MOGC. 2018/02/20.
 */
class AccountsEvent: BaseEvent() {

    companion object {
        const val READ_ACCOUNTS_SUCCESS = 1
        const val READ_ACCOUNTS_EMPTY = 2
    }

    var accounts: List<Account>? = null
}