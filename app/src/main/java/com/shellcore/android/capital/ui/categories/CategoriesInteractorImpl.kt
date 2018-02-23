package com.shellcore.android.capital.ui.categories

class CategoriesInteractorImpl(val repository: CategoriesRepository) : CategoriesInteractor {

    override fun getCategories() {
        repository.getCategories()
    }
}

