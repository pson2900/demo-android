package com.example.domain.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.domain.repository.VersionRepository
import com.example.domain.usecase.VersionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Phạm Sơn at 17:36/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class SyncWorker(appContext: Context, workerParams: WorkerParameters,
                 private val versionChecker: VersionRepository,
) : CoroutineWorker(appContext, workerParams) {
     val tag = "VersionCheckWorker"
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(tag, "Starting version check and data sync...")

                val isLatestVersion = versionChecker.isLatestVersion()

                if (!isLatestVersion || versionChecker.hasNewData()) {
                    Log.d(tag, "Syncing data...")
                    versionChecker.syncData()
                } else {
                    Log.d(tag, "No data sync required.")
                }

                Log.d(tag, "Version check and data sync completed successfully.")
                Result.success()

            } catch (e: Exception) {
                Log.e(tag, "Error during version check and data sync", e)
                Result.failure()
            }
        }
    }
}