package com.yjy.redmine2.server

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.yjy.redmine2.common.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat

object Retrofit {
    val server: Server

    init {
        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor { chain: Interceptor.Chain? ->
                val origin = chain!!.request()
                val request = origin.newBuilder()
                    .addHeader(HEADER_KEY_X_REDMINE_SWITCH_USER, USER_NAME)
                    .addHeader(HEADER_KEY_X_REDMINE_API_KEY, USER_API_KEY)
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
