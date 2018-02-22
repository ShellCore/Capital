package com.shellcore.android.capital.ui.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.widget.Toolbar
import com.shellcore.android.capital.R

/**
 * Created by MOGC. 2018/02/16.
 */
@SuppressLint("Registered")
open class ToolbarActivity: AppCompatActivity() {

    fun setupToolbar(toolbar: Toolbar?) {
        toolbar?.let {
            setActionBar(toolbar)
        }
        actionBar.apply {
            setHomeAsUpIndicator(R.mipmap.ic_launcher_round)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}