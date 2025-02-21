package com.upzi.upzi.screen.user

import androidx.lifecycle.SavedStateHandle
import com.upzi.data.proto.DataStoreManager
import com.upzi.data.remote.UIState
import com.upzi.upzi.core.base.BaseViewModel
import com.upzi.domain.model.MyProfile
import com.upzi.domain.usecase.MyProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
class UserViewModel(val dataStoreManager: DataStoreManager, val myProfileUseCase: MyProfileUseCase, stateHandle: SavedStateHandle) : BaseViewModel(stateHandle) {
    private val _myProfileState: MutableStateFlow<UIState<MyProfile>> = MutableStateFlow(UIState.Loading)
    val myProfileState: StateFlow<UIState<MyProfile>> = _myProfileState

    private val _featureItemState: MutableStateFlow<UIState<List<String>>> =
        MutableStateFlow(UIState.Loading)
    val featureItemState: StateFlow<UIState<List<String>>> = _featureItemState

    suspend fun fetchMyProfile() {
        processApiCall(
            call = { myProfileUseCase.getMyProfile() },
            state = _myProfileState,
            dataKey = USER_PROFILE_KEY
        )
    }

    suspend fun fetchListItem() {
        processApiCall(
            call = { myProfileUseCase.getListItem() },
            state = _featureItemState,
            dataKey = ITEMS
        )
    }

    suspend fun getAuth() {
        dataStoreManager.getAuth().catch {
        }.collect { result ->
        }
    }

    companion object {
        private const val USER_PROFILE_KEY = "user_profile"
        private const val ITEMS = "items"

    }
}