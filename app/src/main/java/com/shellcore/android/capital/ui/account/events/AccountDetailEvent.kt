package com.shellcore.android.capital.ui.account.events

import com.shellcore.android.capital.base.BaseEvent
import com.shellcore.android.capital.db.models.Account

class AccountDetailEvent: BaseEvent() {

    companion object {
        const val CREATE_ACCOUNT_SUCCESS = 1
        const val CREATE_ACCOUNT_ERROR = 2
        const val CHECK_VALID_ACCOUNT_ERROR = 3
    }

    var account: Account? = null
}