package com.example.demo_structure.screen.user

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.data.remote.UIState
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.domain.model.MyProfile

/**
 * Created by Phạm Sơn at 14:42/23/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

object UserViewModelState {
    @Composable
    fun ToMyProfileUiState(modifier: Modifier = Modifier, state: UIState<MyProfile>, onNavigateToLogin: () -> Unit) {
        when (state) {
            is UIState.Error -> {
                Log.d("QQQ", "MyProfileState.Error")
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${(state.appException.message)}", color = ProductXTheme.colors.error)
                }
            }

            is UIState.Loading -> {
                Log.d("QQQ", "MyProfileState.Loading")
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AppLoadingWheel(contentDesc = "LoadingWheel")
                }
            }

            is UIState.Success -> {
                Log.d("QQQ", "MyProfileState.ProfileSuccess")
                UserContent(
                    modifier = modifier.fillMaxSize(),
                    onNavigateToLogin = onNavigateToLogin,
                    myProfile = state.data
                )
            }
        }
    }


    @Composable
    fun ToFeatureItemUiState(modifier: Modifier = Modifier, state: UIState<List<String>>, onNavigateToLogin: () -> Unit) {
        when (state) {
            is UIState.Error -> {
                Log.d("QQQ", "FeatureItemState.Error")
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${(state.appException.message)}", color = ProductXTheme.colors.error)
                }
            }

            is UIState.Loading -> {
                Log.d("QQQ", "FeatureItemState.Loading")
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AppLoadingWheel(contentDesc = "LoadingWheel")
                }
            }

            is UIState.Success -> {
                Log.d("QQQ", "FeatureItemState.FeatureItemSuccess")
            }
        }
    }

}
