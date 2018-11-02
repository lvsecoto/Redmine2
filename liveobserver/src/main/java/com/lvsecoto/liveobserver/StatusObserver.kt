package com.lvsecoto.liveobserver

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<Resource<T>>.status(
    owner: LifecycleOwner,
    isLoading: Observer<Resource<T>>? = null,
    isSuccess : Observer<Resource<T>>? = null,
    isError : Observer<Resource<T>>? = null
) {
    observe(owner, Observer {
        when(it.status) {
            Status.SUCCESS -> isSuccess?.onChanged(it)
            Status.ERROR -> isError?.onChanged(it)
            Status.LOADING -> isLoading?.onChanged(it)
        }
    })
}
