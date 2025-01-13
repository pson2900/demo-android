package com.example.demo_structure.screen.user

sealed interface UserState {
    /**
     * The feed is still loading.
     */
    data object Loading : UserState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data object Success : UserState
}