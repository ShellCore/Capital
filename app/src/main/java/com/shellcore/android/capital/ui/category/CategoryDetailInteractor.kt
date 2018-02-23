package com.shellcore.android.capital.ui.category

import com.shellcore.android.capital.db.models.Category

interface CategoryDetailInteractor {
    fun saveCategory(category: Category)

}