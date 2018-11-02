package com.lvsecoto.liveobserver.mocker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.lvsecoto.liveobserver.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Rule
import org.junit.Test

private const val TEST_DATA = "data"

val LOADING_DATA = Resource.loading(null)
val SUCCESS_DATA = Resource.success(TEST_DATA)
val ERROR_DATA = Resource.error("err", TEST_DATA)

val mockOwner: LifecycleOwner
    get() {

        val owner: LifecycleOwner = mock {
        }

        val lifecycle = LifecycleRegistry(owner).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        whenever(owner.lifecycle).thenReturn(lifecycle)
        return owner
    }

class OwnerMockerTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun testMocker() {

        val owner: LifecycleOwner = mockOwner

        val observer = mock<Observer<String>>()

        MutableLiveData<String>().apply {
            value = "123"
            observe(owner, observer)
        }

        verify(observer).onChanged("123")
    }

}