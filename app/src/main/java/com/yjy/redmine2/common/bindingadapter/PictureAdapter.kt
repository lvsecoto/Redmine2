package com.yjy.redmine2.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.yjy.redmine2.GlideApp

@BindingAdapter("server_pic_url")
fun serverImageUrl(imageView: ImageView, serverPicUrl: String) {
    GlideApp.with(imageView)
//        .load(serverPicUrl.replaceFirst("http://127.0.0.1:8099", "123"))
        .load(serverPicUrl.replaceFirst("http://(127.0.0.1):8099".toRegex(), ""))
        .centerCrop()
        .into(imageView)
}
