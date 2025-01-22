package com.example.demo_structure.core.base

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.UIState
import com.example.demo_structure.JobDetail
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 09:55/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
abstract class BaseViewModel constructor(val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: Flow<String> get() = _errorMessage

    protected fun handleError(error: Throwable) {
        _errorMessage.value = error.message ?: "Unknown error"
    }

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

    protected inline fun <reified T> wrapperApiCall(
        crossinline call: suspend () -> Flow<T>,
        stateFlow: MutableStateFlow<UIState<T>>,
        dataKey: String? = null
    ) {
        viewModelScope.launch {
            val tag = "wrapperApiCall"
            Log.d(tag, "Starting API call for dataKey: $dataKey")
            val savedData = dataKey?.let { loadFromSavedState<T>(it) }
            if (savedData == null) {
                Log.d(tag, "No saved data found for dataKey: $dataKey, emitting loading state")
                stateFlow.value = UIState.Loading
                delay(3000L)
            } else {
                Log.d(tag, "Loaded data from saved state: $savedData for dataKey: $dataKey, emitting success")
                stateFlow.value = UIState.Success<T>(savedData)
            }
            try {
                call()
                    .catch { error ->
                        Log.e(tag, "API call failed with catch for dataKey: $dataKey", error)
                        stateFlow.value = UIState.Error(error)
                    }
                    .collect { data ->
                        Log.d(tag, "API call success with collect: $data for dataKey: $dataKey")
                        if (dataKey != null) {
                            Log.d(tag, "Saving the data using the dataKey: $dataKey")
                            saveToSavedState(dataKey, data)
                        }
                        stateFlow.value = UIState.Success(data)
                    }
            } catch (e: Exception) {
                stateFlow.value = UIState.Error(e)
            }
        }
    }
}

@Composable
fun <T> DataStateWrapper(
    modifier: Modifier = Modifier,
    state: UIState<T>,
    onLoadingContent: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AppLoadingWheel(contentDesc = "LoadingWheel")
        }
        Log.d("QQQ", "onLoadingContent")
    },
    onErrorContent: @Composable (message: String) -> Unit = { message ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Error: $message", color = ProductXTheme.colors.error)
        }
        Log.d("QQQ", "onErrorContent")
    },
    onSuccessContent: @Composable (data: T) -> Unit
) {
    when (state) {
        is UIState.Loading -> onLoadingContent()
        is UIState.Success -> onSuccessContent(state.data)
        is UIState.Error -> onErrorContent("")
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DataStateWrapperPreview() {
    ProductXPreviewWrapper {
        DataStateWrapper<JobDetail>(
            state = UIState.Loading,
            onSuccessContent = {}
        )
    }
}