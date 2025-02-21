package com.upzi.data.remote.response

import com.upzi.domain.model.Register

class RegisterResponse(val status: String, val message: String, val data: String) {
    fun toDomain(): Register {
        return Register(isSuccess = status == "success")
    }
}