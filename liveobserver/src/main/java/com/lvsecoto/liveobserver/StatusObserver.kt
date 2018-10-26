package com.lvsecoto.liveobserver

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.lvsecoto.liveobserver.Status.*

internal class StatusChangeObserver<T>(
    private val onSuccess: Observer<Resource<T>>? = null,
    private val onError: Observer<Resource<T>>? = null,
    private val isLoading: Observer<Resource<T>>? = null
) : Observer<Resource<T>> {

    private var lastStatus: Status? = null

    override fun onChanged(t: Resource<T>) {
        when (t.status) {
            SUCCESS -> {
                if (lastStatus == LOADING) {
                    onSuccess?.onChanged(t)
                }
            }
            ERROR -> {
            }
            LOADING -> {
            }
        }

        lastStatus = t.status
    }
}

fun <T> LiveData<Resource<T>>.watch(
    owner: LifecycleOwner,
    onSuccess: Observer<Resource<T>>
) {
    this.observe(
        owner, StatusChangeObserver(
            onSuccess = onSuccess
        )
    )
}
