package com.example.demo_structure.screen.login

import com.example.demo_structure.screen.create_pin.PinCodeState
import com.example.domain.model.Authentication

sealed interface LoginState {
    object Idle : LoginState
    data class Loading(val isLoading: Boolean) : LoginState
    data class LoginSuccess(val authentication: Authentication) : LoginState
    data class Error(val msg: String): LoginState
}