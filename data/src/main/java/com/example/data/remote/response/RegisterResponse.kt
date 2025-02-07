package com.example.data.remote.response

import com.example.domain.model.Register

class RegisterResponse(val status: String, val message: String, val data: String) {
    fun toDomain(): Register {
        return Register(isSuccess = status == "success")
    }
}