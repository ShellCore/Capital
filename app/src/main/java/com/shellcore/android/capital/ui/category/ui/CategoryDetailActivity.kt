package com.shellcore.android.capital.ui.category.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import com.shellcore.android.capital.CapitalApplication
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.ui.base.ToolbarActivity
import com.shellcore.android.capital.ui.category.CategoryDetailPresenter
import com.shellcore.android.capital.utils.showMessage
import kotlinx.android.synthetic.main.activity_category.*
import javax.inject.Inject

class CategoryDetailActivity : ToolbarActivity(), CategoryDetailView {

    companion object {
        const val CATEGORY_KEY = "category"
    }

    private lateinit var category: Category
    private lateinit var snackbar: Snackbar

    @Inject
    lateinit var presenter: CategoryDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setupToolbar(toolbar as Toolbar)
        setupComponents()
        setupInjection()
        getCategory()

        presenter.onCreate()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        snackbar.show()
    }

    override fun hideProgress() {
        snackbar.dismiss()
    }

    override fun enableInputs() {
        setInputsEnabled(true)
    }

    override fun disableInputs() {
        setInputsEnabled(false)
    }

    override fun showMessage(message: String) {
        cnsCategoryDetail.showMessage(message)
    }

    override fun returnToCategoryList(resultCode: Int) {
        setResult(resultCode)
        finish()
    }

    private fun setInputsEnabled(enabled: Boolean) {
        tilName.editText!!.isEnabled = enabled
        btnCancel.isEnabled = enabled
        btnAccept.isEnabled = enabled
    }

    private fun setupComponents() {
        snackbar = Snackbar.make(cnsCategoryDetail, R.string.default_progress_wait, Snackbar.LENGTH_SHORT)

        btnCancel.setOnClickListener {
            returnToCategoryList(-1)
        }
        btnAccept.setOnClickListener {
            saveCategory()
        }
    }

    private fun setupInjection() {
        val app = application as CapitalApplication
        val component = app.getCategoryDetailComponent(this, this)
        component.inject(this)
    }

    private fun getCategory() {
        if (intent.hasExtra(CATEGORY_KEY)) {
            category = intent.getParcelableExtra(CATEGORY_KEY)
            tilName.editText!!.setText(category.name)
        } else {
            category = Category()
        }
    }

    private fun saveCategory() {
        category.apply {
            name = tilName.editText!!.text.toString()
            presenter.saveCategory(this)
        }
    }
}

