package com.example.data.repository

import com.example.data.remote.network.ApiService
import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyEmail
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(val apiService: ApiService): AuthRepository {
    override suspend fun verifyEmail(): Flow<VerifyEmail> {
        return flow {
            emit(apiService.verifyEmail().data.toDomain())
        }
    }

    override suspend fun sendOtp(): Flow<SendOtp> {
        return flow {
            emit(apiService.sendOtp().data.toDomain())
        }
    }
}