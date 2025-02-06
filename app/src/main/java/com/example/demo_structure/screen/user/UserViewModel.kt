package com.example.demo_structure.screen.user

import androidx.lifecycle.SavedStateHandle
import com.example.data.remote.UIState
import com.example.demo_structure.core.base.BaseViewModel
import com.example.domain.model.MyProfile
import com.example.domain.usecase.MyProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class UserViewModel(private val myProfileUseCase: MyProfileUseCase, stateHandle: SavedStateHandle) : BaseViewModel(stateHandle) {
    private val _myProfileState: MutableStateFlow<UIState<MyProfile>> = MutableStateFlow(UIState.Loading)
    val myProfileState: StateFlow<UIState<MyProfile>> = _myProfileState

    private val _featureItemState: MutableStateFlow<UIState<List<String>>> = MutableStateFlow(UIState.Loading)
    val featureItemState: StateFlow<UIState<List<String>>> = _featureItemState

    suspend fun fetchMyProfile() {
        wrapperApiCall(
            call = myProfileUseCase.getMyProfile(),
            state = _myProfileState,
            dataKey = USER_PROFILE_KEY
        )
    }

    suspend fun fetchListItem() {
        wrapperApiCall(
            call = myProfileUseCase.getListItem(),
            state = _featureItemState,
            dataKey = ITEMS
        )
    }

    companion object {
        private const val USER_PROFILE_KEY = "user_profile"
        private const val ITEMS = "items"

    }
}