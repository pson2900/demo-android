package com.upzi.data.remote.response

import com.upzi.domain.model.VerifyEmail

data class VerifyEmailResponse(val found: Boolean) {
    fun toDomain(): VerifyEmail {
        return VerifyEmail(found = found)
    }
}