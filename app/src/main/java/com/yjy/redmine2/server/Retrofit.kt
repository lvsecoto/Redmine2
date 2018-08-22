package com.yjy.redmine2.server

import com.android.example.github.util.LiveDataCallAdapterFactory
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    val server = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build().create(Server::class.java)
}
