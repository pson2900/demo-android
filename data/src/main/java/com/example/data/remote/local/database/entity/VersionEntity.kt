package com.example.data.remote.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Version

/**
 * Created by Phạm Sơn at 18:07/8/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Entity(tableName = "version")
class VersionEntity(
    @PrimaryKey
    val id: String,
    val version: String
)

fun VersionEntity.asVersionModel() = Version(
    versionId = id,
    version = version
)