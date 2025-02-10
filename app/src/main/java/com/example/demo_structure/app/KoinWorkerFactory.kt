package com.example.demo_structure.app

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.domain.repository.VersionRepository
import com.example.domain.workers.SyncWorker
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * Created by Phạm Sơn at 18:01/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class KoinWorkerFactory : WorkerFactory(), KoinComponent {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            SyncWorker::class.java.name -> {
                SyncWorker(appContext, workerParameters, get<VersionRepository>())
            }

            else ->
                // Return null, or default Worker() instance, if you don't have any Worker
                // with such a name, or pass the creation to the default Android worker factory
                null
        }
    }
}