package com.example.demo_structure.screen.create_pin

import com.example.demo_structure.screen.otp.OtpState
import com.example.domain.model.Authentication

sealed interface PinCodeState {
    object Idle : PinCodeState
    data class Loading(val isLoading: Boolean) : PinCodeState
    data class RegisterSuccess(val isSuccess: Boolean, val email: String,val passCode: String) : PinCodeState
    data class UpdatePasswordSuccess(val isSuccess: Boolean) : PinCodeState
    data class LoginSuccess(val authentication: Authentication) : PinCodeState
    data class Error(val msg: String): PinCodeState
}