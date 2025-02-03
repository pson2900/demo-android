package com.example.demo_structure.screen.community

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo_structure.core.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 16:16/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class CommunityViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    val state: StateFlow<CommunityState> = MutableStateFlow<CommunityState>(CommunityState.Loading).apply {
        viewModelScope.launch {
            delay(2000)
            value = CommunityState.Success
        }
    }.asStateFlow()

}

