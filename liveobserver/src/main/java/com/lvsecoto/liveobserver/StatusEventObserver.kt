package com.lvsecoto.liveobserver

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.lvsecoto.liveobserver.Status.*

internal class StatusEventObserver<T>(
    private val onSuccess: Observer<Resource<T>>? = null,
    private val onError: Observer<Resource<T>>? = null,
    private val isLoading: Observer<Resource<T>>? = null
) : Observer<Resource<T>> {

    private var lastStatus: Status? = null

    override fun onChanged(result: Resource<T>) {
        when (result.status) {
            SUCCESS -> {
                if (lastStatus == LOADING) {
                    onSuccess?.onChanged(result)
                }
            }
            ERROR -> {
                if (lastStatus == LOADING) {
                    onError?.onChanged(result)
                }
            }
            LOADING -> {
                isLoading?.onChanged(result)
            }
        }

        lastStatus = result.status
    }
}

fun <T> LiveData<Resource<T>>.event(
    owner: LifecycleOwner,
    onSuccess: Observer<Resource<T>>,
    onError: Observer<Resource<T>>,
    isLoading: Observer<Resource<T>>
) {
    this.observe(
        owner, StatusEventObserver(
            onSuccess = onSuccess,
            onError = onError,
            isLoading = isLoading
        )
    )
}
