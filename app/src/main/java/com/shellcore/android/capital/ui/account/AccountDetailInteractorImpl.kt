package com.shellcore.android.capital.ui.account

import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.account.events.AccountDetailEvent

class AccountDetailInteractorImpl(val eventBus: EventBus, private val repository: AccountDetailRepository) : AccountDetailInteractor {

    override fun saveAccount(account: Account) {
        if (accountValid(account)) {
            repository.saveAccount(account)
        } else {
            post(AccountDetailEvent.CHECK_VALID_ACCOUNT_ERROR, "Los datos de la cuenta no son válidos. Favor de verificar.")
        }
    }

    private fun accountValid(account: Account): Boolean {
        if (account.name.isEmpty())
            return false
        if (repository.accountAlreadyExists(account))
            return false
        return true
    }

    private fun post(type: Int, message: String = "") {
        AccountDetailEvent().apply {
            this.type = type
            this.message = message
            eventBus.post(this)
        }
    }
}
