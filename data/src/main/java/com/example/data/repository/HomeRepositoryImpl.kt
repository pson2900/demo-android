package com.example.data.repository

import com.example.data.remote.ErrorMapper
import com.example.data.remote.network.ApiService
import com.example.data.remote.response.UserProfileResponse
import com.example.domain.HomeRepository
import com.example.domain.model.UserProfile


/**
 * Created by Phạm Sơn at 12:23/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class HomeRepositoryImpl(private val apiService: ApiService, private val errorMapper: ErrorMapper) : HomeRepository {

    override suspend fun getWelcomeMessage(): UserProfile {
        return apiService.getUser("a").toDomain()
    }
}