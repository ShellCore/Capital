package com.shellcore.android.capital.ui.category.events

import com.shellcore.android.capital.base.BaseEvent
import com.shellcore.android.capital.db.models.Category

class CategoryDetailEvent: BaseEvent() {

    companion object {
        const val CREATE_CATEGORY_SUCCESS = 1
        const val CREATE_CATEGORY_ERROR = 2
        const val CHECK_VALID_CATEGORY_ERROR = 3
    }

    var category: Category? = null
}