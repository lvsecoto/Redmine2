package com.lvsecoto.redmine2.common.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import com.lvsecoto.redmine2.common.*
import java.io.InputStream

class ServerImageLoader(
    modelLoader: ModelLoader<GlideUrl, InputStream>
) : BaseGlideUrlLoader<String>(modelLoader) {

    private val hostMatcher = "(?<=^http://)127.0.0.1(:\\d+)?(?=/)".toRegex()

    override fun getUrl(model: String?, width: Int, height: Int, options: Options?): String {
        return model?.replaceFirst(hostMatcher, REDMINE_HOST) ?: ""
    }

    override fun getHeaders(model: String?, width: Int, height: Int, options: Options?): Headers? =
        LazyHeaders.Builder()
            .addHeader(
                HEADER_KEY_X_REDMINE_SWITCH_USER,
                USER_NAME
            )
            .addHeader(
                HEADER_KEY_X_REDMINE_API_KEY,
                USER_API_KEY
            )
            .build()

    override fun handles(model: String): Boolean =
        hostMatcher.containsMatchIn(model)

    class Factory : ModelLoaderFactory<String, InputStream> {

        override fun build(unused: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
            return ServerImageLoader(
                unused.build(
                    GlideUrl::class.java,
                    InputStream::class.java
                )
            )
        }

        override fun teardown() {

        }
    }
}