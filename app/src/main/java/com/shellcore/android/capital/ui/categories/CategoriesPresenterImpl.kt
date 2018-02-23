package com.shellcore.android.capital.ui.categories

import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.categories.events.CategoriesEvent
import com.shellcore.android.capital.ui.categories.ui.CategoriesView
import org.greenrobot.eventbus.Subscribe

class CategoriesPresenterImpl(val eventBus: EventBus, var view: CategoriesView?, val interactor: CategoriesInteractor) : CategoriesPresenter {

    override fun onCreate() {
        eventBus.register(this)
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        view = null
    }

    override fun getCategories() {
        view?.showProgress()
        interactor.getCategories()
    }

    @Subscribe
    override fun onEventMainThread(event: CategoriesEvent) {
        view?.hideProgress()
        when(event.type) {
            CategoriesEvent.READ_CATEGORIES_SUCCESS -> updateCategoryList(event.categories!!)
            CategoriesEvent.READ_CATEGORIES_EMPTY -> showEmptyList()
        }
    }

    private fun updateCategoryList(categories: List<Category>) {
        view?.apply {
            updateList(categories)
            showList()
        }
    }

    private fun showEmptyList() {
        view?.hideList()
    }
}
