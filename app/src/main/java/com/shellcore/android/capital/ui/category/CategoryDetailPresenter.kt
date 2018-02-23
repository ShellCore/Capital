package com.shellcore.android.capital.ui.category

import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.ui.category.events.CategoryDetailEvent

interface CategoryDetailPresenter {

    fun onCreate()
    fun onDestroy()

    fun saveCategory(category: Category)

    fun onEventMainThread(event: CategoryDetailEvent)
}