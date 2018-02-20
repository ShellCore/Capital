package com.shellcore.android.capital.ui.accounts.di

import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.accounts.*
import com.shellcore.android.capital.ui.accounts.ui.AccountsView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/20.
 */
@Module
class AccountsModule(private var view: AccountsView) {

    @Provides
    @Singleton
    fun providesAccountsPresenter(eventBus: EventBus, view: AccountsView?, interactor: AccountsInteractor): AccountsPresenter
            = AccountsPresenterImpl(eventBus, view, interactor)

    @Provides
    @Singleton
    fun providesAccountsView(): AccountsView
            = view

    @Provides
    @Singleton
    fun providesAccountsInteractor(repository: AccountsRepository): AccountsInteractor
            = AccountsInteractorImpl(repository)

    @Provides
    @Singleton
    fun providesAccountsRepository(eventBus: EventBus): AccountsRepository
            = AccountsRepositoryImpl(eventBus)
}