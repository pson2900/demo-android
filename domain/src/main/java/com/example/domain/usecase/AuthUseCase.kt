package com.example.domain.usecase

import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyEmail
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class AuthUseCase(private val authRepository: AuthRepository) {

    suspend fun verifyEmail(email: String): Flow<VerifyEmail> {
        return authRepository.verifyEmail(email)
    }

    suspend fun sendOtp(email: String): Flow<SendOtp> {
        return authRepository.sendOtp(email)
    }

    suspend fun forgetPassword(email: String): Flow<SendOtp> {
        return authRepository.forgetPassword(email)
    }
}