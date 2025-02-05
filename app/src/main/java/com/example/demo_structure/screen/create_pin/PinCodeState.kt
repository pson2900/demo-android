package com.example.demo_structure.screen.create_pin

import com.example.demo_structure.screen.otp.OtpState

sealed interface PinCodeState {
    object Idle : PinCodeState
    data class Loading(val isLoading: Boolean) : PinCodeState
    data class Success(val isSuccess: Boolean) : PinCodeState
    data class Error(val msg: String): PinCodeState
}