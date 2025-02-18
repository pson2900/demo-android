package com.example.data.remote.response

import com.example.domain.model.VerifyOtp

data class VerifyOtpResponse(
    val isValid: Boolean,
    val secret: String? = null,
    val message: String? = null
) {
    fun toDomain(): VerifyOtp {
        return VerifyOtp(isValid = isValid, secret = secret ?: "", message = message ?: "")
    }
}