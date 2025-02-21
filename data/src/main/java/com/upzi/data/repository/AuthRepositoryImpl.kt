package com.upzi.data.repository

import com.upzi.data.remote.network.ApiService
import com.upzi.domain.model.Authentication
import com.upzi.domain.model.Register
import com.upzi.domain.model.SendOtp
import com.upzi.domain.model.VerifyEmail
import com.upzi.domain.model.VerifyOtp
import com.upzi.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.HashMap

class AuthRepositoryImpl(val apiService: ApiService): AuthRepository {
    override suspend fun verifyEmail(email: String): Flow<VerifyEmail> {
        val body = HashMap<String, String>()
        body["email"] = email
        return flow {
            emit(apiService.verifyEmail(body).data.toDomain())
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

    override suspend fun verifyOtp(email: String, otp: String): Flow<VerifyOtp> {
        val body = HashMap<String, String>()
        body.put("email", email)
        body.put("otp", otp)
        return flow {
            emit(apiService.verifyOtp(body).data.toDomain())
        }
    }

    override suspend fun register(email: String, password: String,secret: String): Flow<Register> {
        val body = HashMap<String, String>()
        body.put("email", email)
        body.put("password", password)
        body.put("secret", secret)
        return flow {
            emit(apiService.register(body).toDomain())
        }
    }

    override suspend fun updatePassword(email: String, password: String,secret: String): Flow<Register> {
        val body = HashMap<String, String>()
        body.put("email", email)
        body.put("newPassword", password)
        body.put("secret", secret)
        return flow {
            emit(apiService.updatePassword(body).toDomain())
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Authentication> {
        val body = HashMap<String, String>()
        body.put("email", email)
        body.put("password", password)
        return flow {
            emit(apiService.login(body).data.toDomain())
        }
    }
}