package com.shellcore.android.capital.ui.account

import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.account.events.AccountDetailEvent
import com.shellcore.android.capital.ui.account.ui.AccountDetailView
import org.greenrobot.eventbus.Subscribe

class AccountDetailPresenterImpl(val eventBus: EventBus, var view: AccountDetailView?, val interactor: AccountDetailInteractor) : AccountDetailPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun saveAccount(account: Account) {
        interactor.saveAccount(account)
    }

    @Subscribe
    override fun onEventMainThread(event: AccountDetailEvent) {
        when (event.type) {
            AccountDetailEvent.CREATE_ACCOUNT_SUCCESS -> TODO("not implemented")
            AccountDetailEvent.CHECK_VALID_ACCOUNT_ERROR -> TODO("not implemented")
            AccountDetailEvent.CREATE_ACCOUNT_ERROR -> TODO("not implemented")
        }
    }
}