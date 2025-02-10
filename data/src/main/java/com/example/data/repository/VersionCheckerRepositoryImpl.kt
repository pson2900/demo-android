package com.example.data.repository

import com.example.data.remote.local.database.dao.VersionDao
import com.example.data.remote.network.ApiService
import com.example.domain.repository.VersionRepository

/**
 * Created by Phạm Sơn at 17:56/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class VersionCheckerRepositoryImpl(val apiService: ApiService, val versionDao: VersionDao) : VersionRepository {
    override suspend fun isLatestVersion(): Boolean {
//        val latestVersion = apiService.getLatestVersion()
        val latestVersion = "1.0.0"
        return latestVersion == getCurrentVersion()
    }

    private fun getCurrentVersion(): String {
        return "1.0.0"
    }

    override suspend fun hasNewData(): Boolean {
        return apiService.hasNewData()
    }

    override suspend fun syncData() {
        val newData = apiService.getDataVersion()
//        versionDao.insertVersion(newData)
    }
}