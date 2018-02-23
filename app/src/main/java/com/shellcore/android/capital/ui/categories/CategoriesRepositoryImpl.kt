package com.shellcore.android.capital.ui.categories

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.shellcore.android.capital.db.models.Category
import com.shellcore.android.capital.db.models.Category_Table
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.ui.categories.events.CategoriesEvent

class CategoriesRepositoryImpl(val eventBus: EventBus) : CategoriesRepository {

    override fun getCategories() {
        val categories = SQLite.select()
                .from(Category::class.java)
                .orderBy(Category_Table.name, true)
                .queryList()
        if (categories.size > 0) {
            post(CategoriesEvent.READ_CATEGORIES_SUCCESS, categories)
        } else {
            post(CategoriesEvent.READ_CATEGORIES_EMPTY)
        }
    }

    private fun post(type: Int, categories: List<Category>? = null) {
        var event = CategoriesEvent()
        event.apply {
            this.type = type
            this.categories = categories
        }
        eventBus.post(event)
    }
}