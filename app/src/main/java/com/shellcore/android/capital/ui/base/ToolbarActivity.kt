package com.shellcore.android.capital.ui.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

/**
 * Created by MOGC. 2018/02/16.
 */
@SuppressLint("Registered")
open class ToolbarActivity: AppCompatActivity() {

    fun setupToolbar(toolbar: Toolbar?) {
        toolbar?.let {
            setSupportActionBar(toolbar)
        }

        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setHomeButtonEnabled(true)
        }
    }
}