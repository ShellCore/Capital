package com.shellcore.android.capital.libs.base

/**
 * Created by MOGC. 2018/02/15.
 */
interface EventBus {

    fun register(subscriber: Any)
    fun unregister(subscriber: Any)
    fun post(event: Any)
}