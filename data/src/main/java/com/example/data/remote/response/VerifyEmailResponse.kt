package com.example.data.remote.response

import android.util.Log
import com.example.domain.model.MyProfile
import com.example.domain.model.VerifyEmail

data class VerifyEmailResponse(val found: Boolean) {
    fun toDomain(): VerifyEmail {
        return VerifyEmail(found = found)
    }
}