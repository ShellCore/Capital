package com.shellcore.android.capital.ui.categories.ui


import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shellcore.android.capital.CapitalApplication
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.ui.categories.CategoriesPresenter
import com.shellcore.android.capital.ui.categories.ui.adapter.CategoriesAdapter
import com.shellcore.android.capital.ui.categories.ui.adapter.CategoryListener
import com.shellcore.android.capital.ui.category.ui.CategoryDetailActivity
import com.shellcore.android.capital.utils.showMessage
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject

class CategoriesFragment : Fragment(), CategoriesView, CategoryListener {

    companion object {
        const val CREATE_CATEGORY = 1
        const val UPDATE_CATEGORY = 2
    }

    private lateinit var snackbar: Snackbar
    private lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var presenter: CategoriesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponents()
        setupInjection()
        setupAdapter()
        setupList()

        presenter.onCreate()
        presenter.getCategories()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 0)
            presenter.getCategories()
    }

    override fun showProgress() {
        snackbar.show()
    }

    override fun hideProgress() {
        snackbar.dismiss()
    }

    override fun showMessage(message: String) {
        contentCategories.showMessage(message)
    }

    override fun showList() {
        crdCategories.visibility = View.VISIBLE
        txtEmptyList.visibility = View.GONE
    }

    override fun hideList() {
        crdCategories.visibility = View.GONE
        txtEmptyList.visibility = View.VISIBLE
    }

    override fun updateList(categories: List<Category>) {
        categoriesAdapter.updateCategories(categories)
    }

    override fun onClick(category: Category) {
        val intent = Intent(activity, CategoryDetailActivity::class.java).apply {
            putExtra(CategoryDetailActivity.CATEGORY_KEY, category)
        }
        startActivityForResult(intent, UPDATE_CATEGORY)
    }

    private fun setupComponents() {
        snackbar = Snackbar.make(contentCategories, R.string.default_progress_wait, Snackbar.LENGTH_SHORT)
        fltAddCategory.setOnClickListener {
            addCategory()
        }
    }

    private fun setupInjection() {
        val app = activity.application as CapitalApplication
        val component = app.getCategoriesComponent(activity, this)
        component.inject(this)
    }

    private fun setupAdapter() {
        categoriesAdapter = CategoriesAdapter(listOf(), this)
    }

    private fun setupList() {
        recCategories.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            this.adapter = categoriesAdapter
        }
    }

    private fun addCategory() {
        startActivityForResult(Intent(activity, CategoryDetailActivity::class.java), CREATE_CATEGORY)
    }
}
