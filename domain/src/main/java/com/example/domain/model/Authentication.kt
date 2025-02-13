package com.example.domain.model

data class Authentication(
    val accessToken: String? = null,
    val expiresIn: Int? = null,
    val refreshToken: String? = null,
    val refreshExpiresIn: Int? = null,
    val tokenType: String? = null,
    val expiresTime: Long= 0,
    val refreshExpiresTime: Long= 0
) {
    fun getBearerToken(): String? {
        return tokenType + accessToken
    }

}