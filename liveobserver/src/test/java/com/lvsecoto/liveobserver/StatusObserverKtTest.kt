package com.lvsecoto.liveobserver

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lvsecoto.liveobserver.mocker.mockOwner
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test

class PresenterTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun reactIfSourceStateChangeFromLoadingToSuccess() {
        val source = MutableLiveData<Resource<String>>()
        val observer = mock<Observer<Resource<String>>>()

        source.watch(mockOwner, onSuccess = observer)

        source.value = Resource.loading(null)
        verify(observer, never()).onChanged(any())

        source.value = Resource.success("test")
        verify(observer).onChanged(Resource.success("test"))
    }

    fun reqctIfSourceStateChangeFromLoadingToErr() {

    }
}

val toLoadinig: (LiveData<Resource<*>>) -> Unit = {
}
