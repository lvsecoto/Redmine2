package com.yjy.redmine2.server

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.yjy.redmine2.common.LiveDataCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat

object Retrofit {
    val server : Server

    init {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor { chain: Interceptor.Chain? ->
                val origin = chain!!.request()
                val request = origin.newBuilder()
                    .addHeader("X-Redmine-Switch-User", "YuanJunYao")
                    .addHeader("X-Redmine-API-Key", "a5564509fcbcf7c4e437d81789df36b05b9eb823")
                    .method(origin.method(), origin.body())
                    .build()
                chain.proceed(request)
            }
            .build()

        val gson = GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(DateFormat.LONG)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://redmine.jbangit.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build()

        server = retrofit.create(Server::class.java)
    }
}
