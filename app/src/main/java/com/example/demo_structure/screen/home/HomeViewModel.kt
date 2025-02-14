package com.example.demo_structure.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.proto.DataStoreManager
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.education.EducationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

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