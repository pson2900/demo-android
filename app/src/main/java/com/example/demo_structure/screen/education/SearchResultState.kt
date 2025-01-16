package com.example.demo_structure.screen.education

import com.example.demo_structure.SearchJob


/**
 * Created by Phạm Sơn at 20:04/1/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
sealed interface SearchResultState {

    val isLoading: Boolean
    val searchJobs: MutableList<SearchJob>
    val searchInput: String

    /**
     * There are no posts to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class Empty(
        override val isLoading: Boolean = false,
        override val searchJobs: MutableList<SearchJob> = emptyList<SearchJob>().toMutableList(),
        override val searchInput: String
    ) : SearchResultState

    /**
     * There are posts to render, as contained in [postsFeed].
     *
     * There is guaranteed to be a [selectedPost], which is one of the posts from [postsFeed].
     */
    data class SearchResult(
        override val isLoading: Boolean = false,
        override val searchJobs: MutableList<SearchJob>,
        override val searchInput: String
    ) : SearchResultState

    data class Error(
        override val isLoading: Boolean = false,
        override val searchJobs: MutableList<SearchJob> = emptyList<SearchJob>().toMutableList(),
        override val searchInput: String
    ) : SearchResultState

    data class Loading(
        override val isLoading: Boolean,
        override val searchJobs: MutableList<SearchJob> = emptyList<SearchJob>().toMutableList(),
        override val searchInput: String
    ) : SearchResultState

    data class LoadMore(
        override val isLoading: Boolean,
        override val searchJobs: MutableList<SearchJob> = emptyList<SearchJob>().toMutableList(),
        override val searchInput: String
    ) : SearchResultState
}

data class SearchResultViewModelState(
    val searchJobs: MutableList<SearchJob>? = null,
    val isLoadMore: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
    val searchInput: String = "",
) {
    fun toUiState(): SearchResultState =
        if (isLoading) {
            SearchResultState.Loading(isLoading = true, searchInput = searchInput)
        } else {
            if (isLoadMore) {
                SearchResultState.LoadMore(isLoading = true, searchJobs = searchJobs ?: mutableListOf(), searchInput = searchInput)
            } else {
                if (errorMessages.isEmpty()) {
                    if (searchJobs.isNullOrEmpty()) {
                        SearchResultState.Empty(isLoading = false, searchJobs = searchJobs ?: mutableListOf(), searchInput = searchInput)
                    } else {
                        SearchResultState.SearchResult(isLoading = false, searchJobs = searchJobs ?: mutableListOf(), searchInput = searchInput)
                    }

                } else {
                    SearchResultState.Error(isLoading = false, searchJobs = mutableListOf(), searchInput = searchInput)
                }
            }
        }

}

