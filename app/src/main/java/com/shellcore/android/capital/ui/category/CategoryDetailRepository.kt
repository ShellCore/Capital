package com.shellcore.android.capital.ui.category

import com.shellcore.android.capital.db.models.Category

interface CategoryDetailRepository {

    fun saveCategory(category: Category)
    fun categoryAlreadyExists(category: Category): Boolean

}