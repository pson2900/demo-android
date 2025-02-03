package com.example.demo_structure.screen.verify_email

import com.example.demo_structure.screen.login.LoginState


sealed interface EmailState {
    data object Loading : EmailState
    data object Success : EmailState
}