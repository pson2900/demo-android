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
import com.example.data.remote.AppException
import com.example.data.remote.ErrorMapper
import com.example.data.remote.UIState
import com.example.demo_structure.JobDetail
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.google.gson.Gson
import kotlinx.coroutines.delay
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

    protected inline fun <reified T> emitLoading(stateFlow: MutableStateFlow<UIState<T>>) {
        stateFlow.value = UIState.Loading
    }

    protected inline fun <reified T> emitSuccess(stateFlow: MutableStateFlow<UIState<T>>, data: T) {
        stateFlow.value = UIState.Success(data)
    }

    protected inline fun <reified T> emitError(stateFlow: MutableStateFlow<UIState<T>>, appException: AppException) {
        stateFlow.value = UIState.Error(appException)
    }

    protected inline fun <reified T> wrapperApiCall(
        crossinline call: suspend () -> Flow<T>,
        stateFlow: MutableStateFlow<UIState<T>>,
        dataKey: String? = null
    ) {
        viewModelScope.launch {
            val tag = "wrapperApiCall"
            Log.d(tag, "Starting API call for dataKey: $dataKey")
            try {
                call()
                    .onStart {
                        val savedData = dataKey?.let { loadFromSavedState<T>(it) }
                        if (savedData == null) {
                            Log.d(tag, "No saved data for $dataKey, emitting loading state")
                            emitLoading(stateFlow)
                            delay(2000L)
                        } else {
                            Log.d(tag, "Loaded data for $dataKey, emitting success")
                            emitSuccess(stateFlow, savedData)
                        }
                    }
                    .catch { error ->
                        Log.e(tag, "API call failed for $dataKey", error)
                        error.printStackTrace()
                        emitError(stateFlow, ErrorMapper.toAppException(error))
                    }
                    .collect { data ->
                        Log.d(tag, "API call success for $dataKey: $data")
                        dataKey?.let { saveToSavedState(it, data) }
                        emitSuccess(stateFlow, data)
                    }
            } catch (e: CancellationException) {
                Log.d(tag, "API call canceled for dataKey: $dataKey in outer try-catch")
            } catch(e : Exception){
                val exception = ErrorMapper.toAppException(e)
                e.printStackTrace()
                Log.e(tag, "API call failed in outer catch for $dataKey : $exception",e)
                emitError(stateFlow, exception)
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
        Log.d("QQQ", "onErrorContent: ${message}")
    },
    onSuccessContent: @Composable (data: T) -> Unit = {
        Log.d("QQQ", "onSuccessContent")
    }
) {
    when (state) {
        is UIState.Loading -> onLoadingContent()
        is UIState.Success -> onSuccessContent(state.data)
        is UIState.Error -> {

            onErrorContent(state.appException.message ?: "Unknown error")
        }
    }
}

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
}