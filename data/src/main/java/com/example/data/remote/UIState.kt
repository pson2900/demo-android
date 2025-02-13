package com.example.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Created by Phạm Sơn at 11:45/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface UIState<out T> {
    object Idle : UIState<Nothing>
    data class Success<out T>(val data: T) : UIState<T>
    data class Error(val appException: AppException) : UIState<Nothing>
    object Loading : UIState<Nothing>
}


fun <T> Flow<T>.asResult(): Flow<UIState<T>> = map<T, UIState<T>> { UIState.Success(it) }
    .onStart { emit(UIState.Loading) }
    .catch { emit(UIState.Error(ErrorMapper.toAppException(it))) }
