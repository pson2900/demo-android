package com.example.data.remote.response

import com.example.domain.model.Authentication
import java.util.Calendar

data class LoginResponse(
    val accessToken: String? = null,
    val expiresIn: Int? = null,
    val refreshExpiresIn: Int? = null,
    val refreshToken: String? = null,
    val sessionState: String? = null
) {

    fun toDomain(): Authentication {
        val expiresTime = calculateFutureTimeInMillis(expiresIn?:0)
        val refreshExpiresTime = calculateFutureTimeInMillis(refreshExpiresIn ?: 0)

        return Authentication(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresIn = expiresIn,
            refreshExpiresIn = refreshExpiresIn,
            expiresTime = expiresTime,
            refreshExpiresTime = refreshExpiresTime,
            tokenType = "Bearer "
        )
    }

    fun calculateFutureTimeInMillis(secondsToAdd: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, secondsToAdd)
        return calendar.timeInMillis
    }
}