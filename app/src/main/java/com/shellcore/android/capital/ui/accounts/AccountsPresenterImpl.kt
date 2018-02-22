package com.shellcore.android.capital.ui.accounts

import com.shellcore.android.capital.db.models.Account
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.accounts.events.AccountsEvent
import com.shellcore.android.capital.ui.accounts.ui.AccountsView
import org.greenrobot.eventbus.Subscribe

/**
 * Created by MOGC. 2018/02/20.
 */
class AccountsPresenterImpl(val eventBus: EventBus, var view: AccountsView?, private val interactor: AccountsInteractor) : AccountsPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun getAccounts() {
        view?.showProgress()
        interactor.getAccounts()
    }

    @Subscribe
    override fun onEventMainThread(event: AccountsEvent) {
        view?.hideProgress()
        when(event.type) {
            AccountsEvent.READ_ACCOUNTS_SUCCESS -> updateAccountList(event.accounts!!)
            AccountsEvent.READ_ACCOUNTS_EMPTY -> showEmptyList()
        }
    }

    private fun updateAccountList(accounts: List<Account>) {
        view?.apply {
            updateList(accounts)
            showList()
        }
    }

    private fun showEmptyList() {
        view?.apply {
            hideList()
        }
    }
}

