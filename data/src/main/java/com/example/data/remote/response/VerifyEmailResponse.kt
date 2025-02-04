package com.example.data.remote.response

import com.example.domain.model.MyProfile
import com.example.domain.model.VerifyEmail

data class VerifyEmailResponse(val  status: String,val message: String,val data: Data) {
    fun toDomain(): VerifyEmail {
        return VerifyEmail(found = data.found)
    }
}

data class Data(val found: Boolean) {

}