package com.example.data.repository

import android.util.Log
import com.example.data.remote.network.ApiService
import com.example.domain.model.SendOtp
import com.example.domain.model.VerifyEmail
import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import java.util.HashMap

class AuthRepositoryImpl(val apiService: ApiService): AuthRepository {
    override suspend fun verifyEmail(email: String): Flow<VerifyEmail> {
        val body = HashMap<String, String>()
        body.put("email", email)
        return flow {
            emit(apiService.verifyEmail(body).toDomain())
        }
    }

    override suspend fun sendOtp(email: String): Flow<SendOtp> {
        val body = HashMap<String, String>()
        body.put("email", email)
        return flow {
            emit(apiService.sendOtp(body).toDomain())
        }
    }

    override suspend fun forgetPassword(email: String): Flow<SendOtp> {
        val body = HashMap<String, String>()
        body.put("email", email)
        return flow {
            emit(apiService.forgetPassword(body).toDomain())
        }
    }
}