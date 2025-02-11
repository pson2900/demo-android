package com.example.demo_structure.screen.education

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.proto.DataStoreManager
import com.example.demo_structure.core.base.BaseViewModel
import com.example.demo_structure.screen.login.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 16:16/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class EducationViewModel(
    val dataStoreManager: DataStoreManager,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {
    private val searchResultState = MutableStateFlow(
        EducationResultViewModelState(
            isLoading = true,
            searchJobs = mutableListOf(),
            searchInput = ""
        )
    )


    private val _authUiState = MutableStateFlow<EducationState>(EducationState.Loading(false))
    val authUiState: StateFlow<EducationState> = _authUiState.asStateFlow()


    val state = searchResultState
        .map(EducationResultViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            searchResultState.value.toUiState()
        )


    suspend fun getAuth() {
        dataStoreManager.getAuth().catch {
            _authUiState.value = EducationState.AuthError(it.message.toString())
        }.collect { result ->
            result?.let {
                _authUiState.value = EducationState.AuthSuccess(it)
            } ?: run {
                _authUiState.value = EducationState.AuthError("auth error")
            }
        }
    }


    fun logout() {
        viewModelScope.launch {
            dataStoreManager.clearAll()
        }
    }
}

