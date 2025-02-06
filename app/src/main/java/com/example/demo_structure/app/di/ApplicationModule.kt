package com.example.demo_structure.app.di

import com.example.demo_structure.app.AppDispatchers
import com.example.demo_structure.util.ConnectivityManagerNetworkMonitor
import com.example.demo_structure.util.NetworkMonitor
import com.example.demo_structure.util.TimeZoneBroadcastMonitor
import com.example.demo_structure.util.TimeZoneMonitor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 11:21/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

val applicationModule = module {
    factory<CoroutineScope> { CoroutineScope(get()) }
    // Dispatchers
    single<CoroutineDispatcher>(named(AppDispatchers.Default.name)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(AppDispatchers.IO.name)) { Dispatchers.IO }
    // Coroutine Scopes
    single<CoroutineScope> {
        CoroutineScope(SupervisorJob() + get<CoroutineDispatcher>(named(AppDispatchers.Default.name)))
    }

    single<TimeZoneMonitor> {
        TimeZoneBroadcastMonitor(androidContext(), get(), get(named(AppDispatchers.IO.name)))
    }
    single<NetworkMonitor> {
        ConnectivityManagerNetworkMonitor(androidContext(), get(named(AppDispatchers.IO.name)))
    }
}