package com.example.data.remote.response

import android.util.Log
import com.example.domain.model.MyProfile
import com.example.domain.model.VerifyEmail

data class VerifyEmailResponse(val  status: String?= null,val message: String?= null,val data: Data) {
    fun toDomain(): VerifyEmail {
        return VerifyEmail(found = data.found)
    }
}

data class Data(val found: Boolean) {

}