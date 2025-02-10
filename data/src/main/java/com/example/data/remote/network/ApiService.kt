package com.example.data.remote.network

import com.example.data.remote.response.BaseResponse
import com.example.data.remote.response.MyProfileResponse
import com.example.domain.model.Version
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * Created by Phạm Sơn at 11:08/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface ApiService {
    @GET("jobseeker/api/v1/jobseeker-info/profile")
    suspend fun getProfile(): BaseResponse<MyProfileResponse>

    suspend fun getLatestVersion(): String
    suspend fun getDataVersion(): Version
    suspend fun hasNewData(): Boolean
}