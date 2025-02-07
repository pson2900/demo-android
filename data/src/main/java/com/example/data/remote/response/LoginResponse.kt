package com.example.data.remote.response

import com.example.domain.model.Authentication

data  class LoginResponse(val accessToken: String? = null,
                          val expiresIn: Int? = null,
                          val refreshExpiresIn: Int? = null,
                          val refreshToken: String? = null,
                          val sessionState: String? = null) {

    fun toDomain(): Authentication {
        return Authentication(accessToken  = accessToken,
            refreshToken = refreshToken,
            expiresIn = expiresIn,
            tokenType = "Bearer ")
    }
}