package com.shellcore.android.capital.ui.main.ui

import android.os.Bundle
import android.widget.Toolbar
import com.shellcore.android.capital.R
import com.shellcore.android.capital.ui.base.ToolbarActivity
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : ToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbar as Toolbar)
    }
}
