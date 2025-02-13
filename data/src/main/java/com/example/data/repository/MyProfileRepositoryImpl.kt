package com.example.data.repository

import com.example.data.remote.network.ApiService
import com.example.domain.model.MyProfile
import com.example.domain.repository.MyProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Phạm Sơn at 12:23/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class MyProfileRepositoryImpl(val apiService: ApiService) : MyProfileRepository {
    override suspend fun getMyProfile(): Flow<MyProfile> {
        return flow {
            emit(apiService.getProfile().data.toDomain())
        }
    }
}