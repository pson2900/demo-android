package com.example.data.remote.response

import com.example.domain.model.SendOtp


data class SendOtpResponse(val status: String, val message: String, val data: String) {

    fun toDomain(): SendOtp {
        return SendOtp(isSuccess = status == "success")
    }
}