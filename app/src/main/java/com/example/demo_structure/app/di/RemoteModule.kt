package com.example.demo_structure.app.di

import com.example.data.remote.network.ApiService
import com.example.data.remote.network.RetrofitClient
import com.example.data.repository.AuthRepositoryImpl
import com.example.domain.repository.AuthRepository
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 12:05/6/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

val remoteModule = module {
    single<ApiService> { RetrofitClient(get(),true,get()).createService<ApiService>() }
}