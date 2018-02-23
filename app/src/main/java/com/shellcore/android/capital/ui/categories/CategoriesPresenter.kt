package com.shellcore.android.capital.ui.categories

import com.shellcore.android.capital.ui.categories.events.CategoriesEvent

interface CategoriesPresenter {

    fun onCreate()
    fun onDestroy()

    fun getCategories()

    fun onEventMainThread(event: CategoriesEvent)

}