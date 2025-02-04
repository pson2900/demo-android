package com.example.demo_structure.screen.verify_email

import com.example.demo_structure.screen.login.LoginState


sealed interface EmailState {
    data object Loading : EmailState
    data class Success(val found: Boolean) : EmailState
    data class Error(val msg: String): EmailState
}