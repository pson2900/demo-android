package com.example.domain.repository

import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyEmail
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun verifyEmail(email: String): Flow<VerifyEmail>

    suspend fun sendOtp(email: String): Flow<SendOtp>

    suspend fun forgetPassword(email: String): Flow<SendOtp>
}