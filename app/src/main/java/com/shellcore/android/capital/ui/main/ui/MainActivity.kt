package com.shellcore.android.capital.ui.main.ui

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.shellcore.android.capital.R
import com.shellcore.android.capital.ui.accounts.ui.AccountsFragment
import com.shellcore.android.capital.ui.base.ToolbarActivity
import com.shellcore.android.capital.ui.categories.ui.CategoriesFragment
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
        setupDrawer()
    }

    override fun onBackPressed() {
        if (contentMain.isDrawerOpen(GravityCompat.START)) {
            contentMain.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        setOnlySelectedItemChecked(item)
        when (item.itemId) {
            R.id.actionMain -> {
                handleMenuMain()
            }
            R.id.actionRecord -> {
                handleMenuRecord()
            }
            R.id.actionAccounts -> {
                handleMenuAccounts()
            }
            R.id.actionCategories -> {
                handleMenuCategories()
            }
        }
        contentMain.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setOnlySelectedItemChecked(item: MenuItem) {
        for (i in 0..navMain.menu.size() - 1) {
            var otherItem = navMain.menu.getItem(i)
            otherItem.isChecked = false
        }
        item.isChecked = true
    }

    private fun setupDrawer() {
        val drawerToggle = ActionBarDrawerToggle(this, contentMain, toolbar as Toolbar, R.string.drawer_open, R.string.drawer_close)
        contentMain.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun handleMenuMain() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleMenuRecord() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleMenuAccounts() {
        setFragment(AccountsFragment())
    }

    private fun handleMenuCategories() {
        setFragment(CategoriesFragment())
    }

    private fun setFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment)
                .commit()
    }
}
