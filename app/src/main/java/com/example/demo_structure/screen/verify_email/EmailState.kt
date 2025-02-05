package com.example.demo_structure.screen.verify_email

import com.example.demo_structure.screen.login.LoginState


sealed interface EmailState {
    object Idle : EmailState
    data class Loading(val isLoading: Boolean) : EmailState
    data class Success(val found: Boolean) : EmailState
    data class Error(val msg: String): EmailState
}