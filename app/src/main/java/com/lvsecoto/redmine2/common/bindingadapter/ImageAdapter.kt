package com.lvsecoto.redmine2.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.lvsecoto.redmine2.common.glide.GlideApp

@BindingAdapter("serverImage")
fun serverImageUrl(imageView: ImageView, serverPicUrl: String) {
    GlideApp.with(imageView)
        .load(serverPicUrl)
        .into(imageView)
}
