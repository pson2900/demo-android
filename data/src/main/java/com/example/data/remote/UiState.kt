package com.example.data.remote

/**
 * Created by Phạm Sơn at 11:45/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed class UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val errorType: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}