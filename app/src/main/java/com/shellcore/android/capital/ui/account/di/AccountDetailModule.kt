package com.shellcore.android.capital.ui.account.di

import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.account.*
import com.shellcore.android.capital.ui.account.ui.AccountDetailView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/22.
 */
@Module
class AccountDetailModule(val view: AccountDetailView) {

    @Provides
    @Singleton
    fun providesAccountDetailPresenter(eventBus: EventBus, view: AccountDetailView, interactor: AccountDetailInteractor): AccountDetailPresenter
            = AccountDetailPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesAccountDetailView(): AccountDetailView
            = view

    @Provides
    @Singleton
    fun providesAccountDetailInteractor(eventBus: EventBus, repository: AccountDetailRepository): AccountDetailInteractor
            = AccountDetailInteractorImpl(eventBus, repository)

    @Provides
    @Singleton
    fun providesAccountDetailRepository(eventBus: EventBus): AccountDetailRepository
            = AccountDetailRepositoryImpl(eventBus)
}