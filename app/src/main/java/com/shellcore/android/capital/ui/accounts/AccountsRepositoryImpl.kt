package com.shellcore.android.capital.ui.accounts

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.db.models.Account_Table
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.accounts.events.AccountsEvent

/**
 * Created by MOGC. 2018/02/20.
 */
class AccountsRepositoryImpl(val eventBus: EventBus) : AccountsRepository {

    override fun getAccounts() {
        var accounts = SQLite.select()
                .from(Account::class.java)
                .orderBy(Account_Table.name, true)
                .queryList()
        if (accounts.size > 0) {
            post(AccountsEvent.READ_ACCOUNTS_SUCCESS, accounts)
        } else {
            post(AccountsEvent.READ_ACCOUNTS_EMPTY)
        }
    }

    private fun post(eventType: Int, accounts: List<Account>? = null) {
        var event = AccountsEvent()
        event.apply {
            type = eventType
            this.accounts = accounts
        }
        eventBus.post(event)
    }
}