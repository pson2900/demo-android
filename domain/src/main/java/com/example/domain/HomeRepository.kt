package com.example.domain

import com.example.domain.model.UserProfile

/**
 * Created by Phạm Sơn at 12:38/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface HomeRepository {
    suspend fun getWelcomeMessage(): UserProfile
}