package com.yjy.redmine2.common.utils

import android.content.Context

fun Int.fromDp(context: Context) =
    (context.resources.displayMetrics.density * this).toInt()