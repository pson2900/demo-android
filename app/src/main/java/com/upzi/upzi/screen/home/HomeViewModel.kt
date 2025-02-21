package com.upzi.upzi.screen.home

import androidx.lifecycle.SavedStateHandle
import com.upzi.data.proto.DataStoreManager
import com.upzi.upzi.core.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class HomeViewModel(savedStateHandle: SavedStateHandle, val dataStoreManager: DataStoreManager) : BaseViewModel(savedStateHandle){
    private val _authUiState = MutableStateFlow<HomeState>(HomeState.Loading)
    val authUiState: StateFlow<HomeState> = _authUiState.asStateFlow()

    suspend fun getAuth() {
        dataStoreManager.getAuth().catch {
            _authUiState.value = HomeState.Error(errorCode = 1, errorMessage = "")
        }.collect { result ->
            result?.let {
                _authUiState.value = HomeState.Success
            } ?: run {
                _authUiState.value = HomeState.Error(errorCode = 1, errorMessage = "")
            }
        }
    }


}