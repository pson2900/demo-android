package com.upzi.data.remote.network

/**
 * Created by Phạm Sơn at 10:15/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

import com.upzi.data.proto.DataStoreManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient(
    val dataStoreManager: DataStoreManager,
    val isInterceptor: Boolean? = false
) {

    private val BASE_URL = "https://api.xstaging.navigosgroup.site/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .apply {
            if (isInterceptor == true) {
                addInterceptor(HeaderInterceptor(dataStoreManager))
                addInterceptor(AuthInterceptor(dataStoreManager))
            }
            addInterceptor(loggingInterceptor)
        }
        .build()


    val networkApi: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    inline fun <reified T> createService(): T {
        return networkApi.create(T::class.java)
    }
}
