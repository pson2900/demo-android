package com.example.demo_structure.screen.user

import androidx.lifecycle.SavedStateHandle
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.MyProfile
import com.example.domain.usecase.MyProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class UserViewModel(private val myProfileUseCase: MyProfileUseCase, stateHandle: SavedStateHandle) : BaseViewModel(stateHandle) {
    private val _state: MutableStateFlow<UIState<MyProfile>> = MutableStateFlow(UIState.Loading)
    val state: StateFlow<UIState<MyProfile>> = _state.asStateFlow()



    private var _myProfileUser: MyProfile? = loadFromSavedState(USER_PROFILE_KEY)
        set(value) {
            field = value
            saveToSavedState(USER_PROFILE_KEY, value)
        }

    private fun getInitialState(): UIState<MyProfile>? {
        return _myProfileUser?.let {
            UIState.Success(it)
        }

    }

    fun fetchMyProfile() {
        wrapperApiCall(
            call = { myProfileUseCase.getMyProfile() },
            stateFlow = _state,
            dataKey = USER_PROFILE_KEY
        )
    }

    companion object {
        private const val USER_PROFILE_KEY = "user_profile"

    }
}