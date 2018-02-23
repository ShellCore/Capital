package com.shellcore.android.capital.ui.category

import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.account.events.AccountDetailEvent
import com.shellcore.android.capital.ui.category.events.CategoryDetailEvent

class CategoryDetailInteractorImpl(val eventBus: EventBus, val repository: CategoryDetailRepository) : CategoryDetailInteractor {

    override fun saveCategory(category: Category) {
        if (categoryValid(category)) {
            repository.saveCategory(category)
        } else {
            post(CategoryDetailEvent.CHECK_VALID_CATEGORY_ERROR, "Los datos de la categoría no son válidos. Favor de verificar.")
        }
    }

    private fun categoryValid(category: Category): Boolean {
        if (category.name.isEmpty()) {
            return false
        }
        if (repository.categoryAlreadyExists(category)) {
            return false
        }
        return true
    }

    private fun post(type: Int, message: String = "") {
        AccountDetailEvent().apply {
            this.type = type
            this.message = message
            eventBus.post(this)
        }
    }
}

