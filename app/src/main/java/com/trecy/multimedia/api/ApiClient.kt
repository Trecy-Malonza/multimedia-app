package com.trecy.multimedia.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.SECONDS

object ApiClient {
    val okHttpClient= OkHttpClient.Builder()
        .connectTimeout(30, SECONDS)
        .writeTimeout(30,SECONDS)
        .readTimeout(30, SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("Constants.BASEURL")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> buildClient(apiInterface: Class<T>): T{
        return retrofit.create(apiInterface)
    }

}