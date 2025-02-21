package com.upzi.upzi.screen.home

sealed interface HomeState {
    /**
     * The feed is still loading.
     */
    data object Loading: HomeState

    /**
     * The feed is still error.
     */
    data class Error(val errorCode: Int, val errorMessage: String): HomeState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data object Success : HomeState
}