package com.lvsecoto.liveobserver

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ProcessLifecycleOwner
import com.yjy.redmine2.common.Resource
import org.junit.After
import org.junit.Before
import org.junit.Test

class StatusObserverKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun onSuccess() {
        val testLiveData = MutableLiveData<Resource<String>>()
        testLiveData
            .onSuccess(ProcessLifecycleOwner.get(), Observer { })
            .onSuccess(ProcessLifecycleOwner.get(), Observer { })
            .onSuccess(ProcessLifecycleOwner.get(), Observer { })
    }
}