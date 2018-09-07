package com.yjy.redmine2.common.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.yjy.redmine2.common.Resource
import com.yjy.redmine2.common.Status

/**
 * 等[sources]逐个LiveData返回的是True，再最终订阅到[result]
 */
fun <T> queue(sources: List<LiveData<Resource<*>>>, result: LiveData<Resource<T>>) =
    object : MediatorLiveData<Resource<T>>() {

        private val iterator: ListIterator<LiveData<Resource<*>>> = sources.listIterator()

        private var source: LiveData<Resource<*>>

        init {
            check(iterator.hasNext())

            value = Resource.loading(null)

            source = iterator.next()

            addSource(source, object : Observer<Resource<*>> {
                override fun onChanged(sourceResult: Resource<*>) =// 当前返回的是true才订阅下一个
                    when(sourceResult.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            removeSource(source)

                            if (iterator.hasNext()) {
                                source = iterator.next()
                                addSource(source, this)
                            } else {
                                // 队列中没有了，才订阅最终的结果
                                addSource(result) {
                                    value = it
                                }
                            }
                        }
                        Status.ERROR -> {
                            removeSource(source)
                            value = Resource.error(sourceResult.message?:"123", null)
                        }
                    }
            })
        }
    }
