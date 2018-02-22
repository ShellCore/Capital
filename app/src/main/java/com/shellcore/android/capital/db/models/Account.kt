package com.shellcore.android.capital.db.models

import android.os.Parcel
import android.os.Parcelable
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
    : BaseModel(), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(type)
        parcel.writeDouble(balance)
        parcel.writeDouble(limit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Account> {

        const val CASH = 1
        const val DEBIT = 2
        const val CREDIT = 3
        const val SAVING = 4
        const val OTHER = 5

        override fun createFromParcel(parcel: Parcel): Account {
            return Account(parcel)
        }

        override fun newArray(size: Int): Array<Account?> {
            return arrayOfNulls(size)
        }
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