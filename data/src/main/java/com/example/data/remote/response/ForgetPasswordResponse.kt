package com.example.data.remote.response

import com.example.domain.model.SendOtp

class ForgetPasswordResponse (val status: String, val message: String, val data: String) {

    fun toDomain(): SendOtp {
        return SendOtp(isSuccess = status == "success")
    }
}