package com.lvsecoto.liveobserver

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<Resource<T>>.onSuccess(
    owner: LifecycleOwner,
    observer: Observer<Resource<*>>
): LiveData<Resource<T>> {
    return this
}

fun LiveData<Any>.onTest(
    owner: LifecycleOwner,
    observer: Observer<Resource<*>>
){
}
