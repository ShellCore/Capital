package com.shellcore.android.capital.ui.accounts.ui.adapter

import com.shellcore.android.capital.db.models.Account

/**
 * Created by MOGC. 2018/02/20.
 */
interface AccountListener {

    fun onClick(account: Account)
}