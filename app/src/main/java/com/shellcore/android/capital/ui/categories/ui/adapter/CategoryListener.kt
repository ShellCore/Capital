package com.shellcore.android.capital.ui.categories.ui.adapter

import com.shellcore.android.capital.db.models.Category

/**
 * Created by MOGC. 2018/02/23.
 */
interface CategoryListener {

    fun onClick(category: Category)
}