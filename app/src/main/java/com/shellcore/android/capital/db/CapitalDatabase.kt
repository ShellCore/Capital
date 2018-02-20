package com.shellcore.android.capital.db

import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by MOGC. 2018/02/20.
 */
@Database(name = CapitalDatabase.NAME, version = CapitalDatabase.VERSION)
class CapitalDatabase {
    companion object {
        const val NAME = "capital"
        const val VERSION = 1
    }
}