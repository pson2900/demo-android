package com.example.demo_structure.screen.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.core.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 16:38/23/11/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class MainViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    private var _uiState: MutableStateFlow<MainActivityUiState> = MutableStateFlow(MainActivityUiState.Loading)
    var uiState: StateFlow<MainActivityUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.stateIn(
                scope = viewModelScope,
                initialValue = MainActivityUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )
            delay(3000)
            _uiState.value = MainActivityUiState.Success
        }
    }
}