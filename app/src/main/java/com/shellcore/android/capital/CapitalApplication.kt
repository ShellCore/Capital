package com.shellcore.android.capital

import android.app.Activity
import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager
import com.shellcore.android.capital.libs.di.LibsModule
import com.shellcore.android.capital.ui.account.di.AccountDetailComponent
import com.shellcore.android.capital.ui.account.di.AccountDetailModule
import com.shellcore.android.capital.ui.account.di.DaggerAccountDetailComponent
import com.shellcore.android.capital.ui.account.ui.AccountDetailView
import com.shellcore.android.capital.ui.accounts.di.AccountsComponent
import com.shellcore.android.capital.ui.accounts.di.AccountsModule
import com.shellcore.android.capital.ui.accounts.di.DaggerAccountsComponent
import com.shellcore.android.capital.ui.accounts.ui.AccountsView
import com.shellcore.android.capital.ui.categories.di.CategoriesComponent
import com.shellcore.android.capital.ui.categories.di.CategoriesModule
import com.shellcore.android.capital.ui.categories.di.DaggerCategoriesComponent
import com.shellcore.android.capital.ui.categories.ui.CategoriesView
import com.shellcore.android.capital.ui.category.di.CategoryDetailComponent
import com.shellcore.android.capital.ui.category.di.CategoryDetailModule
import com.shellcore.android.capital.ui.category.di.DaggerCategoryDetailComponent
import com.shellcore.android.capital.ui.category.ui.CategoryDetailView

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

    fun getAccountsComponent(activity: Activity, view: AccountsView): AccountsComponent {
        return DaggerAccountsComponent.builder()
                .libsModule(LibsModule(activity))
                .accountsModule(AccountsModule(view))
                .build()
    }

    fun getAccountDetailComponent(activity: Activity, view: AccountDetailView): AccountDetailComponent {
        return DaggerAccountDetailComponent.builder()
                .libsModule(LibsModule(activity))
                .accountDetailModule(AccountDetailModule(view))
                .build()
    }

    fun getCategoriesComponent(activity: Activity, view: CategoriesView): CategoriesComponent {
        return DaggerCategoriesComponent.builder()
                .libsModule(LibsModule(activity))
                .categoriesModule(CategoriesModule(view))
                .build()
    }

    fun getCategoryDetailComponent(activity: Activity, view: CategoryDetailView): CategoryDetailComponent {
        return DaggerCategoryDetailComponent.builder()
                .libsModule(LibsModule(activity))
                .categoryDetailModule(CategoryDetailModule(view))
                .build()
    }
}