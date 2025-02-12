package com.example.demo_structure.app.di

import com.example.data.remote.network.ApiService
import com.example.data.remote.network.AuthInterceptor
import com.example.data.remote.network.HeaderInterceptor
import com.example.data.remote.network.RetrofitClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

/**
 * Created by Phạm Sơn at 12:05/6/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

val remoteModule = module {
//    single<ApiService> { RetrofitClient(HeaderInterceptor(get()),  AuthInterceptor(get(), get())).createService<ApiService>() }

//    factory<AuthInterceptor> { AuthInterceptor(get(), get()) }
//    factory<HeaderInterceptor> { HeaderInterceptor(get()) }
    single<ApiService> { RetrofitClient().createService<ApiService>() }

}