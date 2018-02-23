package com.shellcore.android.capital.ui.category.ui

interface CategoryDetailView {

    fun showProgress()
    fun hideProgress()
    fun enableInputs()
    fun disableInputs()
    fun showMessage(message: String)
    fun returnToCategoryList(resultCode: Int)
}