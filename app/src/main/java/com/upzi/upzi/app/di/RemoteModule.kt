package com.upzi.upzi.app.di

import com.upzi.data.remote.network.ApiService
import com.upzi.data.remote.network.RetrofitClient
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 12:05/6/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

val remoteModule = module {
    single<ApiService> { RetrofitClient(get(), true).createService<ApiService>() }
}