package com.example.demo_structure.core.base

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.AppException
import com.example.data.remote.ErrorMapper
import com.example.data.remote.UIState
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppLoadingWheel
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

/**
 * Created by Phạm Sơn at 09:55/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
abstract class BaseViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {
    inline fun <reified T> saveToSavedState(key: String, value: T) {
        savedStateHandle[key] = Gson().toJson(value)
    }

    inline fun <reified T> loadFromSavedState(key: String): T? {
        try {
            val json: String? = savedStateHandle.get<String>(key)
            return Gson().fromJson(json, T::class.java)
        } catch (e: Exception) {
            return null
        }
    }

    protected inline fun <reified T> emitLoading(state: MutableStateFlow<UIState<T>>) {
        state.value = UIState.Loading
    }

    protected inline fun <reified T> emitSuccess(state: MutableStateFlow<UIState<T>>, data: T) {
        state.value = UIState.Success(data)
    }

    protected inline fun <reified T> emitError(state: MutableStateFlow<UIState<T>>, appException: AppException) {
        state.value = UIState.Error(appException)
    }

    protected inline fun <reified T> wrapperApiCall(
        call: Flow<T>,
        state: MutableStateFlow<UIState<T>>,
        dataKey: String? = null,
    ) {
        val tag = "wrapperApiCall"
        trace("wrapperApiCall") {
            Log.d(tag, "Starting API call for dataKey: $dataKey")
            viewModelScope.launch {
                call.onStart {
                    Log.d(tag, "API flow started: $dataKey")
                    val savedData = dataKey?.let { loadFromSavedState<T>(it) }
                    if (savedData == null) {
                        Log.d(tag, "No saved data for $dataKey, emitting loading state")
                        emitLoading(state)
                    } else {
                        Log.d(tag, "Loaded data for $dataKey, emitting success")
                        emitSuccess(state, savedData)
                    }
                }

                    .catch {
                        if (it is CancellationException) {
                            Log.d(tag, "API call canceled for dataKey: $dataKey in outer try-catch")
                        } else {
                            val exception = ErrorMapper.toAppException(it)
                            Log.e(tag, "API call failed in outer catch for $dataKey : $exception", it)
                            emitError(state, exception)
                        }

                    }
                    .collect { data ->
                        Log.d(tag, "API call success for $dataKey: $data")
                        dataKey?.let {
                            saveToSavedState(it, data)
                        }

                        emitSuccess(state, data)
                    }
            }
        }

    }

}

@Composable
fun <T> UiStateWrapper(
    uiState: UIState<T>,
    onLoadingContent: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AppLoadingWheel(contentDesc = "LoadingWheel")
        }
        Log.d("QQQ", "onLoadingContent")
    },
    onErrorContent: @Composable (message: String) -> Unit = { message ->
        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp).fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Error: $message", color = ProductXTheme.colorScheme.error)
        }
        Log.d("QQQ", "onErrorContent: ${message}")
    },
    onSuccessContent: @Composable (state: T) -> Unit = {
        Log.d("QQQ", "onSuccessContent")
    }
) {
    when (uiState) {
        is UIState.Loading -> onLoadingContent()
        is UIState.Success -> onSuccessContent(uiState.data)
        is UIState.Error -> onErrorContent(uiState.appException.message ?: "Unknown error")
    }
}

/*
@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DataStateWrapperPreview() {
    AppPreviewWrapper {
        DataStateWrapper<JobDetail>(
            state = UIState.Loading,
            onSuccessContent = {}
        )
    }
}*/
