package com.lvsecoto.liveobserver

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.nhaarman.mockitokotlin2.*
import org.junit.Rule
import org.junit.Test

class PresenterTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

//    private val owner : LifecycleOwner
//        get() {
//            val lifecycle = mock<Lifecycle> {
//                on {currentState} doReturn Lifecycle.State.RESUMED
//            }
//
//            return mock {
//                on {getLifecycle()} doReturn lifecycle
//            }
//        }

    @Test
    fun reactIfSourceBecomeFromLoadingToSuccess() {
        val lifecycle = mock<Lifecycle> {
            on {currentState} doReturn Lifecycle.State.RESUMED
        }

        val owner = mock<LifecycleOwner> {
            on {getLifecycle()} doReturn lifecycle
        }

        val source = MutableLiveData<Resource<String>>()
        val observer = mock<Observer<Resource<String>>>()
//        source.watch(owner,
//            onSuccess = observer
//        )
        source.observe(owner, observer)

        source.value = Resource.loading(null)
        verify(observer, never()).onChanged(any())

        source.value = Resource.success("test")
        verify(observer).onChanged(Resource.success("test"))

    }

    @Test
    fun showTitleTest() {

        val lifecycle = mock<Lifecycle> {
            on { currentState } doReturn Lifecycle.State.RESUMED
        }
        val owner = mock<LifecycleOwner> {
            on { getLifecycle() } doReturn lifecycle
        }

        val observer = mock<Observer<String>>()

        MutableLiveData<String>().apply {
            value = "123"
            observe(owner, observer)
        }

        verify(observer).onChanged("123")
    }

    @Test
    fun showTitleTestPassed() {

        val owner: LifecycleOwner
        val lifecycle: LifecycleRegistry

        owner = mock {
        }
        lifecycle = LifecycleRegistry(owner)
        whenever(owner.lifecycle).thenReturn(lifecycle)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)


        val observer = mock<Observer<String>>()

        MutableLiveData<String>().apply {
            value = "123"
            observe(owner, observer)
        }

        verify(observer).onChanged("123")
    }
}