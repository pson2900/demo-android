package com.example.data.remote.network

import com.example.data.remote.response.BaseResponse
import com.example.data.remote.response.MyProfileResponse
import com.example.data.remote.response.SendOtpResponse
import com.example.data.remote.response.VerifyEmailResponse
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.HashMap

/**
 * Created by Phạm Sơn at 11:08/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface ApiService {
    @GET("jobseeker/api/v1/jobseeker-info/profile")
    suspend fun getProfile(): BaseResponse<MyProfileResponse>

    @POST("/secure/js/verify-email")
    suspend fun verifyEmail(@Body body: HashMap<String, String>): VerifyEmailResponse

    @POST("/secure/js/send-otp")
    suspend fun sendOtp(@Body body: HashMap<String, String>): SendOtpResponse

    @POST("/secure/js/forget-password")
    suspend fun forgetPassword(@Body body: HashMap<String, String>): SendOtpResponse

}