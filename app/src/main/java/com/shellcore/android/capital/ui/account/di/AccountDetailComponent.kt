package com.shellcore.android.capital.ui.account.di

import com.shellcore.android.capital.libs.di.LibsModule
import com.shellcore.android.capital.ui.account.ui.AccountDetailActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/22.
 */
@Singleton
@Component(modules = [LibsModule::class, AccountDetailModule::class])
interface AccountDetailComponent {

    fun inject(activity: AccountDetailActivity)
}