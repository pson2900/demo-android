package com.example.demo_structure.app.di

import com.example.data.remote.network.ApiService
import com.example.data.remote.network.RetrofitClient
import com.example.demo_structure.util.ConnectivityManagerNetworkMonitor
import com.example.demo_structure.util.NetworkMonitor
import com.example.demo_structure.util.TimeZoneBroadcastMonitor
import com.example.demo_structure.util.TimeZoneMonitor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:21/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

val networkModule = module {
    factory { CoroutineScope(get()) }
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<ApiService> { RetrofitClient.createService<ApiService>() }
    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(androidContext(), get()) }
//    single<TimeZoneMonitor> { TimeZoneBroadcastMonitor(androidContext(), get(), get()) }
}