package com.example.domain.model

data class Authentication(
    val accessToken: String? = null,
    val expiresIn: Int? = null,
    val refreshToken: String? = null,
    val tokenType: String? = null
) {

}