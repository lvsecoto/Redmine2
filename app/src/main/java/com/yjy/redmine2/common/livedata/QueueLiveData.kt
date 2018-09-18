package com.yjy.redmine2.common.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.yjy.redmine2.common.Resource
import com.yjy.redmine2.common.Status

/**
 * 等[sources]逐个LiveData返回的是True，再最终订阅到[result]
 */
@Deprecated("")
fun <T, R> queue(
    sources: List<LiveData<out Resource<T>>>,
    result: LiveData<Resource<R>>
): LiveData<Resource<R>> =
    object : MediatorLiveData<Resource<R>>() {

        private val iterator: ListIterator<LiveData<out Resource<T>>> = sources.listIterator()

        private var source: LiveData<out Resource<T>>

        init {
            check(iterator.hasNext())

            value = Resource.loading(null)

            source = iterator.next()

            addSource(source, object : Observer<Resource<T>> {
                override fun onChanged(sourceResult: Resource<T>) =// 当前返回的是true才订阅下一个
                    when (sourceResult.status) {
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
                            value = Resource.error(
                                """
                                    from source[${iterator.previousIndex()}]: ${sourceResult.message}
                                """.trimIndent(),
                                null
                            )
                        }
                    }
            })
        }
    }

fun <R, T> queueApi(fromDB: LiveData<R>, vararg calls: LiveData<out Resource<T>>) =
    object : MediatorLiveData<Resource<R>>() {
        private val callIterator = calls.iterator()
        private var source = callIterator.next()

        init {
            value = Resource.loading(null)
            addSource(fromDB) {
                value = Resource.loading(it)
            }

            addSource(source, object : Observer<Resource<T>> {
                override fun onChanged(callResult: Resource<T>?) {
                    when (callResult?.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            removeSource(source)
                            if (callIterator.hasNext()) {
                                source = callIterator.next()
                                addSource(source, this)
                            } else {
                                removeSource(fromDB)
                                addSource(fromDB) {
                                    value = Resource.success(it)
                                }
                            }
                        }
                        Status.ERROR -> {
                            removeSource(source)
                            removeSource(fromDB)
                            addSource(fromDB) {
                                value = Resource.error(callResult.message ?: "", it)
                            }
                        }
                    }
                }
            })
        }
    }
