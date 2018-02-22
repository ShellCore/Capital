package com.shellcore.android.capital.ui.accounts.di

import com.shellcore.android.capital.libs.di.LibsModule
import com.shellcore.android.capital.ui.accounts.ui.AccountsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/20.
 */
@Singleton
@Component(modules = [LibsModule::class, AccountsModule::class])
interface AccountsComponent {
    fun inject(fragment: AccountsFragment)
}