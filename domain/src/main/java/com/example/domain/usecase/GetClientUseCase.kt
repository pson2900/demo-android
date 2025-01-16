package com.example.domain.usecase

import com.example.domain.repository.HomeRepository


/**
 * Created by Phạm Sơn at 11:24/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class GetClientUseCase (private val homeRepository: HomeRepository) {
    suspend fun execute() = homeRepository.getWelcomeMessage()
}