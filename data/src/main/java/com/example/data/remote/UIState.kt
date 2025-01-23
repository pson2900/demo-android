package com.example.data.remote

/**
 * Created by Phạm Sơn at 11:45/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val appException: AppException) : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
}