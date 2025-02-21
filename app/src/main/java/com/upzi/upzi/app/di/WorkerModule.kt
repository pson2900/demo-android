package com.upzi.upzi.app.di

import com.upzi.upzi.app.KoinWorkerFactory
import org.koin.dsl.module

/**
 * Created by Phạm Sơn at 23:00/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
val workerModule = module {
    single { KoinWorkerFactory() }
}