package com.example.domain.usecase

import com.example.domain.model.Authentication
import com.example.domain.model.Register
import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyEmail
import com.example.domain.model.VerifyOtp
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

    suspend fun verifyOtp(email: String, otp: String): Flow<VerifyOtp> {
        return authRepository.verifyOtp(email, otp)
    }

    suspend fun register(email: String, password: String, secret: String): Flow<Register> {
        return authRepository.register(email, password, secret)
    }

    suspend fun updatePassword(email: String, password: String, secret: String): Flow<Register> {
        return authRepository.updatePassword(email, password, secret)
    }

    suspend fun login(email: String, password: String): Flow<Authentication> {
        return authRepository.login(email, password)
    }

    suspend fun renewToken(refreshToken: String): Flow<Authentication> {
        return authRepository.renewToken(refreshToken)
    }
}