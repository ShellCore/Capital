package com.shellcore.android.capital.ui.categories.di

import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.categories.*
import com.shellcore.android.capital.ui.categories.ui.CategoriesView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/23.
 */
@Module
class CategoriesModule(val view: CategoriesView) {

    @Provides
    @Singleton
    fun providesCategoriesPresenter(eventBus: EventBus, view: CategoriesView, interactor: CategoriesInteractor): CategoriesPresenter
            = CategoriesPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesView(): CategoriesView
            = view

    @Provides
    @Singleton
    fun providesCategoriesInteractor(repository: CategoriesRepository): CategoriesInteractor
            = CategoriesInteractorImpl(repository)

    @Provides
    @Singleton
    fun providesCategoriesRepository(eventBus: EventBus): CategoriesRepository
            = CategoriesRepositoryImpl(eventBus)
}
