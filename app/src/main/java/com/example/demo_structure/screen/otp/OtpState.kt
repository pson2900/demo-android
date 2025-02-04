package com.example.demo_structure.screen.otp


sealed interface OtpState {
    data class Loading(val isLoading: Boolean) : OtpState
    data class Success(val isSuccess: Boolean) : OtpState
    data class Error(val msg: String): OtpState
}