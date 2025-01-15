package com.example.demo_structure.screen.login

sealed interface LoginState {
    /**
     * The feed is still loading.
     */
    data object Loading : LoginState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data object Success : LoginState
}