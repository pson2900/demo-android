package com.upzi.data.remote.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.upzi.data.remote.local.database.entity.VersionEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phạm Sơn at 21:51/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Dao
interface VersionDao {
    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertVersion(version: Version)*/

    @Query("SELECT * FROM version WHERE id = :versionId")
    fun getVersion(versionId: String): Flow<VersionEntity>
}