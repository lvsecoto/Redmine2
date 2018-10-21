package com.lvsecoto.redmine2.common.glide

import android.annotation.SuppressLint
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CheckResult")
@GlideExtension()
class GlideExtension private constructor() {

    companion object {
        @GlideOption
        fun miniThumb(options: RequestOptions) {
            options
                .fitCenter()
                .override(123)
        }
    }
}

