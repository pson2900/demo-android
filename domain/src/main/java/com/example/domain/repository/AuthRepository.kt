package com.example.domain.repository

import com.example.domain.model.VerifyEmail
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun verifyEmail(): Flow<VerifyEmail>
}