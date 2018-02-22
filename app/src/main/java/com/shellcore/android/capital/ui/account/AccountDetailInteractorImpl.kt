package com.shellcore.android.capital.ui.account

import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.account.events.AccountDetailEvent

class AccountDetailInteractorImpl(val eventBus: EventBus, val repository: AccountDetailRepository) : AccountDetailInteractor {

    override fun saveAccount(account: Account) {
        if (accountValid(account)) {
            repository.saveAccount(account)
        } else {
            post(AccountDetailEvent.CHECK_VALID_ACCOUNT_ERROR)
        }
    }

    private fun accountValid(account: Account): Boolean {
        TODO("not implemented")
        return false
    }

    private fun post(type: Int) {
        var event = AccountDetailEvent().apply {
            this.type = type
            eventBus.post(this)
        }
    }
}

