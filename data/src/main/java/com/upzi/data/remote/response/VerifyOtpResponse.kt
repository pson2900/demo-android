package com.upzi.data.remote.response

import com.upzi.domain.model.VerifyOtp

data class VerifyOtpResponse(
    val isValid: Boolean,
    val secret: String? = null,
    val message: String? = null
) {
    fun toDomain(): VerifyOtp {
        return VerifyOtp(isValid = isValid, secret = secret ?: "", message = message ?: "")
    }
}