package com.example.demo_structure.app

import android.app.Application
import androidx.compose.runtime.Composable
import com.example.demo_structure.app.di.dataModule
import com.example.demo_structure.app.di.domainModule
import com.example.demo_structure.app.di.networkModule
import com.example.demo_structure.app.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Phạm Sơn at 09:56/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

object KoinSetup {
    fun init(application: Application) {
        startKoin {
            androidLogger(Level.DEBUG) // Bật debug log
            androidContext(application)
            modules(
                listOf(networkModule, dataModule, domainModule, presentationModule)
            )
        }
    }
}