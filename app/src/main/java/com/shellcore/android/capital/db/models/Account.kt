package com.shellcore.android.capital.db.models

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.NotNull
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.CapitalDatabase

/**
 * Created by MOGC. 2018/02/20.
 */
@Table(database = CapitalDatabase::class)
data class Account(
        @PrimaryKey(autoincrement = true) @Column var id: Long = 0,
        @Column @NotNull var name: String = "",
        @Column @NotNull var type: Int = 0,
        @Column @NotNull var balance: Double = 0.0,
        @Column var limit: Double = 0.0)
    : BaseModel() {

    companion object {
        const val CASH = 1
        const val DEBIT = 2
        const val CREDIT = 3
        const val SAVING = 4
        const val OTHER = 5
    }

    fun getAccountType(): Int {
        var stringId = 0
        when (type) {
            CASH -> stringId = R.string.accounts_type_cash
            DEBIT -> stringId = R.string.accounts_type_debit
            CREDIT -> stringId = R.string.accounts_type_credit
            SAVING -> stringId = R.string.accounts_type_saving
            OTHER -> stringId = R.string.accounts_type_other
        }
        return stringId
    }
}