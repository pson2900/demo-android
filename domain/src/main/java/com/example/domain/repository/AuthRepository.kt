package com.example.domain.repository

import com.example.domain.model.Authentication
import com.example.domain.model.Register
import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyEmail
import com.example.domain.model.VerifyOtp
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