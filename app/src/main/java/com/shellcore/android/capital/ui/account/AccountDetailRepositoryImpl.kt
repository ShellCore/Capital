package com.shellcore.android.capital.ui.account

import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.account.events.AccountDetailEvent

class AccountDetailRepositoryImpl(val eventBus: EventBus) : AccountDetailRepository {

    override fun saveAccount(account: Account) {
        if (account.save()) {
            post(AccountDetailEvent.CREATE_ACCOUNT_SUCCESS, account)
        } else {
            post(AccountDetailEvent.CREATE_ACCOUNT_ERROR)
        }
    }

    private fun post(type: Int, account: Account? = null) {
        var event = AccountDetailEvent().apply {
            this.type = type
            this.account = account
            eventBus.post(this)
        }
    }
}