package com.shellcore.android.capital.ui.categories.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.shellcore.android.capital.R
import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.utils.inflate
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(var categories: List<Category>, private var listener: CategoryListener): RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_category), listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(categories[position])

    override fun getItemCount() = categories.size

    fun updateCategories(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val listener: CategoryListener): RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {
            with(itemView) {
                txtCategory.text = category.name
                cnsCategory.setOnClickListener {
                    listener.onClick(category)
                }
            }
        }
    }
}