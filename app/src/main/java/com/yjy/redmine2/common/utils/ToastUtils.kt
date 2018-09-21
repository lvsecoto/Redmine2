package com.yjy.redmine2.common.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(toast: String?) {
    Toast.makeText(this.context, toast, Toast.LENGTH_LONG).show()
}