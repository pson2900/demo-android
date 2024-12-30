package com.example.demo_structure.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
    fun <T> saveData(key: String, value: T) {
        savedStateHandle[key] = value
    }

    // Lấy dữ liệu từ SavedStateHandle
    fun <T> getData(key: String): T? {
        return savedStateHandle[key]
    }
    fun <T> getInitialData(key: String): T? {
        return savedStateHandle.get<T>(key) // Lấy giá trị từ Navigation
    }
}