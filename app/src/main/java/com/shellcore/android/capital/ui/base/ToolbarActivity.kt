package com.shellcore.android.capital.ui.base

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toolbar

/**
 * Created by MOGC. 2018/02/16.
 */
open class ToolbarActivity: AppCompatActivity() {

    private var view: View? = null

    fun setupToolbar(toolbar: Toolbar?) {
        toolbar?.let {
            setActionBar(toolbar)
        }
    }
}