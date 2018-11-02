package com.lvsecoto.liveobserver

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lvsecoto.liveobserver.mocker.ERROR_DATA
import com.lvsecoto.liveobserver.mocker.LOADING_DATA
import com.lvsecoto.liveobserver.mocker.SUCCESS_DATA
import com.lvsecoto.liveobserver.mocker.mockOwner
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StatusEventObserverTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var source: MutableLiveData<Resource<String>>

    private lateinit var successObserver: Observer<Resource<String>>
    private lateinit var errorObserver: Observer<Resource<String>>
    private lateinit var loadingObserver : Observer<Resource<String>>

    @Before
    fun setUp() {
        source = MutableLiveData()
        loadingObserver = mock()
        successObserver = mock()
        errorObserver = mock()

        source.event(
            mockOwner,
            onSuccess = successObserver,
            onError = errorObserver,
            isLoading = loadingObserver
        )

    }

    @Test()
    fun fromLoadingToSuccess() {
        source.value = LOADING_DATA
        verifyInvoked(true, false, false)

        source.value = SUCCESS_DATA
        verifyInvoked(false, true, false)
    }

    @Test
    fun fromLoadingToError() {
        source.value = LOADING_DATA
        verifyInvoked(true, false, false)

        source.value = ERROR_DATA
        verifyInvoked(false, false, true)
    }

    @Test
    fun fromLoadingToLoading() {
        source.value = LOADING_DATA
        verifyInvoked(true, false, false)

        source.value = LOADING_DATA
        verifyInvoked(true, false, false)
    }

    @Test
    fun fromNoLoadingToNoLoading() {
        source.value = SUCCESS_DATA
        verifyInvoked(false, false, false)

        source.value = SUCCESS_DATA
        verifyInvoked(false, false, false)

        source.value = ERROR_DATA
        verifyInvoked(false, false, false)

        source.value = ERROR_DATA
        verifyInvoked(false, false, false)

        source.value = SUCCESS_DATA
        verifyInvoked(false, false, false)
    }

    private fun verifyInvoked(
        loading: Boolean,
        success: Boolean,
        error: Boolean
    ) {

        if (loading) {
            verify(loadingObserver).onChanged(LOADING_DATA)
        } else {
            verify(loadingObserver, never()).onChanged(any())
        }

        if (success) {
            verify(successObserver).onChanged(SUCCESS_DATA)
        } else {
            verify(successObserver, never()).onChanged(any())
        }

        if (error) {
            verify(errorObserver).onChanged(ERROR_DATA)
        } else {
            verify(errorObserver, never()).onChanged(any())
        }

        clearInvocations(loadingObserver, successObserver, errorObserver)
    }

}
