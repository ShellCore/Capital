package com.shellcore.android.capital.ui.accounts

/**
 * Created by MOGC. 2018/02/20.
 */
class AccountsInteractorImpl(private val repository: AccountsRepository) : AccountsInteractor {

    override fun getAccounts() {
        repository.getAccounts()
    }
}

