package com.shellcore.android.capital.libs

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.shellcore.android.capital.libs.base.ImageLoader

/**
 * Created by MOGC. 2018/02/15.
 */
class GlideImageLoader(private val requestManager: RequestManager) : ImageLoader {

    override fun load(view: ImageView, url: String) {
        requestManager.load(Uri.parse(url))
                .apply (
                    RequestOptions.fitCenterTransform()
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                ).into(view)
    }
}