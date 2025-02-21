package com.upzi.domain.repository

import com.upzi.domain.model.Authentication
import com.upzi.domain.model.Register
import com.upzi.domain.model.SendOtp
import com.upzi.domain.model.VerifyEmail
import com.upzi.domain.model.VerifyOtp
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun verifyEmail(email: String): Flow<VerifyEmail>

    suspend fun sendOtp(email: String): Flow<SendOtp>

    suspend fun forgetPassword(email: String): Flow<SendOtp>

    suspend fun verifyOtp(email: String,otp: String): Flow<VerifyOtp>

    suspend fun register(email: String,password: String, secret: String): Flow<Register>

    suspend fun updatePassword(email: String,password: String, secret: String): Flow<Register>

    suspend fun login(email: String,password: String): Flow<Authentication>
}