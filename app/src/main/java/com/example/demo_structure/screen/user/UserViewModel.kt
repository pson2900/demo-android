package com.example.demo_structure.screen.user

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.MyProfile
import com.example.domain.usecase.MyProfileUseCase
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
class UserViewModel(private val myProfileUseCase: MyProfileUseCase, stateHandle: SavedStateHandle) : BaseViewModel(stateHandle) {
    private val _state: MutableStateFlow<UIState<MyProfile>> = MutableStateFlow(UIState.Loading)
    val state: StateFlow<UIState<MyProfile>> = _state.asStateFlow()

    init {
        wrapperApiCall(
            call = { myProfileUseCase.getMyProfile() },
            stateFlow = _state,
            dataKey = USER_KEY
        )
    }

    private var _myProfileUser: MyProfile? = loadFromSavedState(USER_KEY)
        set(value) {
            field = value
            saveToSavedState(USER_KEY, value)
        }

    private fun getInitialState(): UIState<MyProfile>? {
        return _myProfileUser?.let {
            UIState.Success(it)
        }

    }

    private fun fetchMyProfile() {
        viewModelScope.launch {
            val initialValue = getInitialState()
            if (initialValue == null) {
                _state.value = UIState.Loading
            } else {
                _state.value = initialValue
            }
            delay(3000)
            myProfileUseCase.getMyProfile()
                .catch { error -> _state.value = UIState.Error(error) }
                .collect { user ->
                    _myProfileUser = user
                    _state.value = UIState.Success(user)
                }
        }
    }

    companion object {
        private const val USER_KEY = "user"

    }
}