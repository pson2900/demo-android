package com.example.demo_structure.screen.verify_email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.screen.login.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VerifyEmailViewModel  : ViewModel() {
    val menuUiState: StateFlow<EmailState> = MutableStateFlow<EmailState>(EmailState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = EmailState.Success
        }
    }.asStateFlow()

}