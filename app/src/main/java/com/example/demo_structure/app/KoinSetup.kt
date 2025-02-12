package com.example.demo_structure.app

import android.app.Application
import com.example.demo_structure.app.di.applicationModule
import com.example.demo_structure.app.di.dataModule
import com.example.demo_structure.app.di.databaseModule
import com.example.demo_structure.app.di.domainModule
import com.example.demo_structure.app.di.presentationModule
import com.example.demo_structure.app.di.remoteModule
import com.example.demo_structure.app.di.workerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Phạm Sơn at 09:56/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

enum class AppDispatchers {
    Default,
    IO,
}

object KoinSetup {
    fun init(application: Application) {
        startKoin {
            androidLogger(Level.DEBUG) // Bật debug log
            androidContext(application)
            printLogger(Level.DEBUG)
            modules(
                listOf(applicationModule, remoteModule, dataModule, domainModule, presentationModule, databaseModule, )
            )
        }
    }
}