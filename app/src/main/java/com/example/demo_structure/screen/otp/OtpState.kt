package com.example.demo_structure.screen.otp

import com.example.demo_structure.screen.verify_email.EmailState


sealed interface OtpState {
    object Idle : OtpState
    data class Loading(val isLoading: Boolean) : OtpState
    data class Success(val isSuccess: Boolean) : OtpState
    data class Error(val msg: String): OtpState
}