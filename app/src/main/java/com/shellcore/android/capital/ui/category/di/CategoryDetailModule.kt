package com.shellcore.android.capital.ui.category.di

import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.category.*
import com.shellcore.android.capital.ui.category.ui.CategoryDetailView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/23.
 */
@Module
class CategoryDetailModule(val view: CategoryDetailView) {

    @Provides
    @Singleton
    fun providesCategoryDetailPresenter(eventBus: EventBus, view: CategoryDetailView, interactor: CategoryDetailInteractor): CategoryDetailPresenter
            = CategoryDetailPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesView(): CategoryDetailView
            = view

    @Provides
    @Singleton
    fun providesCategoryDetailInteractor(eventBus: EventBus, repository: CategoryDetailRepository): CategoryDetailInteractor
            = CategoryDetailInteractorImpl(eventBus, repository)

    @Provides
    @Singleton
    fun providesCategoryDetailRepository(eventBus: EventBus): CategoryDetailRepository
            = CategoryDetailRepositoryImpl(eventBus)
}