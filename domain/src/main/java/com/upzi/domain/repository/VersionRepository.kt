package com.upzi.domain.repository

/**
 * Created by Phạm Sơn at 17:37/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface VersionRepository {
    suspend fun isLatestVersion(): Boolean
    suspend fun hasNewData(): Boolean
    suspend fun syncData()
}