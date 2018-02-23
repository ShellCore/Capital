package com.shellcore.android.capital.ui.categories.events

import com.shellcore.android.capital.base.BaseEvent
import com.shellcore.android.capital.db.models.Category

class CategoriesEvent: BaseEvent() {

    companion object {
        const val READ_CATEGORIES_SUCCESS = 1
        const val READ_CATEGORIES_EMPTY = 2
    }

    var categories: List<Category>? = null
}