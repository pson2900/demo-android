package com.example.domain.usecase

import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyEmail
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class AuthUseCase(private val authRepository: AuthRepository) {

    suspend fun verifyEmail(): Flow<VerifyEmail> {
        delay(3000L)
        return authRepository.verifyEmail()
    }

    suspend fun sendOtp(): Flow<SendOtp> {
        delay(3000L)
        return authRepository.sendOtp()
    }
}