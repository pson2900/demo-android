package com.upzi.data.remote.response

import com.upzi.domain.model.SendOtp


data class SendOtpResponse(val status: String, val message: String, val data: String) {

    fun toDomain(): SendOtp {
        return SendOtp(isSuccess = status == "success", message= data)
    }
}