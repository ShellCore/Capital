package com.shellcore.android.capital.ui.account

import com.shellcore.android.capital.db.models.Account

/**
 * Created by MOGC. 2018/02/21.
 */
interface AccountDetailInteractor {

    fun saveAccount(account: Account)
}