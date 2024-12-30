package com.example.data.remote.network

import com.example.data.remote.response.UserProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Phạm Sơn at 11:08/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface ApiService {
    @GET("user/{id}")
    suspend fun getUser(@Path("id") userId: String): UserProfileResponse
}