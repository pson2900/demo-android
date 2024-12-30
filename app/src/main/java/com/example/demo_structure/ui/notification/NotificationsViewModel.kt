package com.example.demo_structure.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.demo_structure.core.base.BaseViewModel

class NotificationsViewModel constructor(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}