package com.shellcore.android.capital.libs

import com.shellcore.android.capital.libs.base.EventBus

class GreenRobotEventBus(val eventBus: org.greenrobot.eventbus.EventBus) : EventBus {

    override fun register(subscriber: Any) {
        eventBus.register(subscriber)
    }

    override fun unregister(subscriber: Any) {
        eventBus.unregister(subscriber)
    }

    override fun post(event: Any) {
        eventBus.post(event)
    }
}