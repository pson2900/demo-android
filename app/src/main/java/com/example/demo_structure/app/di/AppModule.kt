package com.example.demo_structure.app.di

import com.example.data.repository.HomeRepositoryImpl
import com.example.data.remote.network.ApiService
import com.example.data.remote.network.RetrofitClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Phạm Sơn at 11:21/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

val networkModule = module {
    single<ApiService> { RetrofitClient.createService<ApiService>() }
}