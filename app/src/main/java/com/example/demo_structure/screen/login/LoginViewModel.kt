package com.example.demo_structure.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class LoginViewModel : ViewModel() {
    val menuUiState: StateFlow<LoginState> = MutableStateFlow<LoginState>(LoginState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = LoginState.Success
        }
    }.asStateFlow()

}