package com.shellcore.android.capital.ui.category.di

import com.shellcore.android.capital.libs.di.LibsModule
import com.shellcore.android.capital.ui.category.ui.CategoryDetailActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/23.
 */
@Singleton
@Component(modules = [LibsModule::class, CategoryDetailModule::class])
interface CategoryDetailComponent {

    fun inject(activity: CategoryDetailActivity)
}