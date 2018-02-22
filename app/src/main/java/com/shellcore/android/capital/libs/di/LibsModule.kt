package com.shellcore.android.capital.libs.di

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.shellcore.android.capital.libs.GlideImageLoader
import com.shellcore.android.capital.libs.GreenRobotEventBus
import com.shellcore.android.capital.libs.base.EventBus
import com.shellcore.android.capital.libs.base.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MOGC. 2018/02/15.
 */
@Module
class LibsModule(private val activity: Activity) {

    @Provides
    @Singleton
    fun providesEventBus(eventBus: org.greenrobot.eventbus.EventBus): EventBus
            = GreenRobotEventBus(eventBus)

    @Provides
    @Singleton
    fun providesLibraryEventBus(): org.greenrobot.eventbus.EventBus
            = org.greenrobot.eventbus.EventBus.getDefault()

    @Provides
    @Singleton
    fun providesImageLoader(requestManager: RequestManager): ImageLoader
            = GlideImageLoader(requestManager)

    @Provides
    @Singleton
    fun providesRequestManager(activity: Activity): RequestManager
            = Glide.with(activity)

    @Provides
    @Singleton
    fun providesActivity(): Activity
            = activity
}