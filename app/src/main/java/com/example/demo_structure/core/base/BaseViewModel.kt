package com.example.demo_structure.core.base

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.UiState
import com.example.demo_structure.JobDetail
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.theme.ProductXTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 09:55/29/12/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
abstract class BaseViewModel constructor(val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    protected fun handleError(error: Throwable) {
        _errorMessage.value = error.message ?: "Unknown error"
    }

    fun <T> saveToSavedState(key: String, value: T) {
        savedStateHandle[key] = value
    }

    fun <T> loadFromSavedState(key: String): T? {
        return savedStateHandle.get<T>(key)
    }

    fun <T> getInitialData(key: String): T? {
        return savedStateHandle.get<T>(key)
    }

    protected fun <T> handleApiCall(
        call: suspend () -> T,
        stateFlow: MutableStateFlow<UiState<T>>,
        dataKey: String? = null
    ) {
        viewModelScope.launch {
            stateFlow.value = UiState.Loading
            try {
                val savedData = dataKey?.let { loadFromSavedState<T>(it) }
                val data = savedData ?: call()
                if (dataKey != null) {
                    saveToSavedState(dataKey, data)
                }
                stateFlow.value = UiState.Success(data)
            } catch (e: Exception) {
                stateFlow.value = UiState.Error(e.localizedMessage ?: "An error has occurred")
            }
        }
    }
}

@Composable
fun <T> DataStateWrapper(
    modifier: Modifier = Modifier,
    uiState: UiState<T>,
    onLoadingContent: @Composable () -> Unit = {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    },
    onErrorContent: @Composable (message: String) -> Unit = { message ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Error: $message", color = ProductXTheme.colors.error)
        }
    },
    onSuccessContent: @Composable (data: T) -> Unit
) {
    when (uiState) {
        is UiState.Loading -> onLoadingContent()
        is UiState.Success -> onSuccessContent(uiState.data)
        is UiState.Error -> onErrorContent("")
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DataStateWrapperPreview() {
    ProductXPreviewWrapper {
        DataStateWrapper<JobDetail>(
            uiState = UiState.Error("Error"),
            onSuccessContent = {}
        )
    }
}