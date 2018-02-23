package com.shellcore.android.capital.ui.categories.di

import com.shellcore.android.capital.libs.di.LibsModule
import com.shellcore.android.capital.ui.categories.ui.CategoriesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/23.
 */
@Singleton
@Component(modules = [LibsModule::class, CategoriesModule::class])
interface CategoriesComponent {

    fun inject(fragment: CategoriesFragment)
}