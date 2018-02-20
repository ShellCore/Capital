package com.shellcore.android.capital.utils

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.NumberFormat
import java.util.*

/**
 * Created by MOGC. 2018/02/20.
 */
fun View.showMessage(message: String, duration: Int = Snackbar.LENGTH_SHORT) = Snackbar.make(this, message, duration).show()

fun ViewGroup.inflate(resource: Int): View = LayoutInflater.from(context).inflate(resource, this, false)

fun Double.toCurrency() = NumberFormat.getCurrencyInstance(Locale.getDefault())
        .format(this)