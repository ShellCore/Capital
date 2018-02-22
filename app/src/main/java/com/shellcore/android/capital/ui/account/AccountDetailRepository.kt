package com.shellcore.android.capital.ui.account

import com.shellcore.android.capital.db.models.Account

interface AccountDetailRepository {

    fun saveAccount(account: Account)
}