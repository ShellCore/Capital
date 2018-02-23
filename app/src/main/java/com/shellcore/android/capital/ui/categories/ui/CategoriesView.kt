package com.shellcore.android.capital.ui.categories.ui

import com.shellcore.android.capital.db.models.Category

interface CategoriesView {

    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)

    fun showList()
    fun hideList()
    fun updateList(categories: List<Category>)
}