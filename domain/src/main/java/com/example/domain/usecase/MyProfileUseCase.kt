package com.example.domain.usecase

import com.example.domain.model.MyProfile
import com.example.domain.repository.MyProfileRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Phạm Sơn at 14:02/17/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class MyProfileUseCase(private val myProfileRepository: MyProfileRepository) {
    suspend fun getMyProfile(): Flow<MyProfile> {
        return myProfileRepository.getMyProfile()
    }
}