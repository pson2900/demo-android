package com.upzi.data.remote.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.upzi.data.remote.local.database.dao.VersionDao
import com.upzi.data.remote.local.database.entity.VersionEntity

/**
 * Created by Phạm Sơn at 17:59/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Database(
    entities = [
        VersionEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun versionDao(): VersionDao
}