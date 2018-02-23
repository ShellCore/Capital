package com.shellcore.android.capital.ui.category

import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.category.events.CategoryDetailEvent
import com.shellcore.android.capital.ui.category.ui.CategoryDetailView
import org.greenrobot.eventbus.Subscribe

class CategoryDetailPresenterImpl(val eventBus: EventBus, var view: CategoryDetailView?, val interactor: CategoryDetailInteractor) : CategoryDetailPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun saveCategory(category: Category) {
        view?.apply {
            disableInputs()
            showProgress()
        }
        interactor.saveCategory(category)
    }

    @Subscribe
    override fun onEventMainThread(event: CategoryDetailEvent) {
        when (event.type) {
            CategoryDetailEvent.CREATE_CATEGORY_SUCCESS -> returnToCategoryList()
            CategoryDetailEvent.CREATE_CATEGORY_ERROR -> showErrorMessage(event.message)
            CategoryDetailEvent.CHECK_VALID_CATEGORY_ERROR -> showErrorMessage(event.message)
        }
    }

    private fun returnToCategoryList() {
        view?.returnToCategoryList(0)
    }

    private fun showErrorMessage(message: String) {

    }
}

