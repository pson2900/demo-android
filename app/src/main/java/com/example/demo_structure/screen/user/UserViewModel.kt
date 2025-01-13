package com.example.demo_structure.screen.user

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
class UserViewModel : ViewModel() {
    val menuUiState: StateFlow<UserState> = MutableStateFlow<UserState>(UserState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = UserState.Success
        }
    }.asStateFlow()

}