package com.shellcore.android.capital

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager

/**
 * Created by MOGC. 2018/02/15.
 */
class CapitalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupDatabase()
    }

    override fun onTerminate() {
        teardownDatabase()
        super.onTerminate()
    }

    private fun setupDatabase() {
        FlowManager.init(this)
    }

    private fun teardownDatabase() {
        FlowManager.destroy()
    }
}