package com.example.domain.usecase

import com.example.domain.repository.HomeRepository
import com.example.domain.model.UserProfile

/**
 * Created by Phạm Sơn at 12:23/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class HomeUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(): UserProfile {
        return homeRepository.getWelcomeMessage()
    }
}