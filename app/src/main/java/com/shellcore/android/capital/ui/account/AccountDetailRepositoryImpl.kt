package com.shellcore.android.capital.ui.account

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.db.models.Account_Table
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.account.events.AccountDetailEvent

class AccountDetailRepositoryImpl(val eventBus: EventBus) : AccountDetailRepository {

    override fun saveAccount(account: Account) {
        if (account.save()) {
            post(AccountDetailEvent.CREATE_ACCOUNT_SUCCESS, account)
        } else {
        post(AccountDetailEvent.CREATE_ACCOUNT_ERROR, message = "Hubo un error en el almacenamiento de la cuenta.")
        }
    }

    override fun accountAlreadyExists(account: Account): Boolean {
        val searchedAccount = SQLite.select()
                .from(Account::class.java)
                .where(Account_Table.name.eq(account.name))
                .and(Account_Table.id.notEq(account.id))
                .querySingle()
        return searchedAccount != null
    }

    private fun post(type: Int, account: Account? = null, message: String = "") {
        AccountDetailEvent().apply {
            this.type = type
            this.account = account
            this.message = message
            eventBus.post(this)
        }
    }
}