package com.upzi.domain.repository

import com.upzi.domain.model.MyProfile
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phạm Sơn at 13:31/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface MyProfileRepository {
    suspend fun getMyProfile(): Flow<MyProfile>
}