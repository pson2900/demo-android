package com.example.data.remote.network

import com.example.data.remote.response.BaseResponse
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.MyProfileResponse
import com.example.data.remote.response.RegisterResponse
import com.example.data.remote.response.SendOtpResponse
import com.example.data.remote.response.VerifyEmailResponse
import com.example.data.remote.response.VerifyOtpResponse
import retrofit2.http.Body
import com.example.domain.model.Version
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.HashMap

/**
 * Created by Phạm Sơn at 11:08/28/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface ApiService {
    @GET("/jobseeker/api/v1/profile")
    suspend fun getProfile(): BaseResponse<MyProfileResponse>

    @POST("/secure/js/verify-email")
    suspend fun verifyEmail(@Body body: HashMap<String, String>): BaseResponse<VerifyEmailResponse>

    @POST("/secure/js/send-otp")
    suspend fun sendOtp(@Body body: HashMap<String, String>): SendOtpResponse

    @POST("/secure/js/forget-password")
    suspend fun forgetPassword(@Body body: HashMap<String, String>): SendOtpResponse


    @POST("/secure/js/verify-otp")
    suspend fun verifyOtp(@Body body: HashMap<String, String>): BaseResponse<VerifyOtpResponse>


    @POST("/secure/js/register")
    suspend fun register(@Body body: HashMap<String, String>): RegisterResponse

    @POST("/secure/js/update-password")
    suspend fun updatePassword(@Body body: HashMap<String, String>): RegisterResponse

    @POST("/secure/js/login")
    suspend fun login(@Body body: HashMap<String, String>): BaseResponse<LoginResponse>

    @POST("/secure/js/renew-token")
    suspend fun renewToken(@Body body: HashMap<String, String>): BaseResponse<LoginResponse>


    suspend fun getLatestVersion(): String
    suspend fun getDataVersion(): Version
    suspend fun hasNewData(): Boolean

}