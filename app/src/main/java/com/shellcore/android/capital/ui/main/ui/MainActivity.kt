package com.shellcore.android.capital.ui.main.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.widget.Toolbar
import com.shellcore.android.capital.R
import com.shellcore.android.capital.ui.accounts.ui.AccountsActivity
import com.shellcore.android.capital.ui.base.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by MOGC. 2018/02/15.
 */
class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(toolbar as Toolbar)
        navMain.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionMain -> handleMenuMain()
            R.id.actionRecord -> handleMenuRecord()
            R.id.actionAccounts -> handleMenuAccounts()
            R.id.actionCategories -> handleMenuCategories()
        }
        contentMain.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleMenuMain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleMenuRecord() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleMenuAccounts() {
        startActivity(Intent(this, AccountsActivity::class.java))
    }

    private fun handleMenuCategories() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
