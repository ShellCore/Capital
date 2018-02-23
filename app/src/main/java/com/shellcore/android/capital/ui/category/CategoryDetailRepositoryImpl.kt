package com.shellcore.android.capital.ui.category

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.db.models.Category_Table
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.category.events.CategoryDetailEvent

class CategoryDetailRepositoryImpl(val eventBus: EventBus) : CategoryDetailRepository {

    override fun saveCategory(category: Category) {
        if (category.save()) {
            post(CategoryDetailEvent.CREATE_CATEGORY_SUCCESS, category)
        } else {
            post(CategoryDetailEvent.CREATE_CATEGORY_ERROR, message = "Hubo un error en el almacenamiento de la categoría.")
        }
    }

    override fun categoryAlreadyExists(category: Category): Boolean {
        val searchedCategory = SQLite.select()
                .from(Category::class.java)
                .where(Category_Table.name.eq(category.name))
                .and(Category_Table.id.notEq(category.id))
                .querySingle()
        return searchedCategory != null
    }

    private fun post(type: Int, category: Category? = null, message:String = "") {
        CategoryDetailEvent().apply {
            this.type = type
            this.category = category
            this.message = message
            eventBus.post(this)
        }
    }
}