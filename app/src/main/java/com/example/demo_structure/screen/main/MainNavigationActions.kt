package com.example.demo_structure.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import com.example.demo_structure.core.component.composableWith
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.screen.job_detail.JobDetailRoute

/**
 * Created by Phạm Sơn at 16:37/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
object Destinations {
    const val HOME_ROUTE = "home"
    const val SEARCH_ROUTE = "home"
    const val USER_ROUTE = "home"
    const val JOB_DETAIL_ROUTE = "job_detail"
    const val JOB_DETAIL_ID_KEY = "jobId"
    const val ORIGIN = "origin"
}

object MainNavigationActions {
    private fun toScreen(modifier: Modifier, navGraphBuilder: NavGraphBuilder, appState: AppState) {
        navGraphBuilder.apply {
            composableWith(route = Destinations.HOME_ROUTE) { backStackEntry ->
                MainContent(
                    modifier = modifier,
                    onNavigateToJobDetail = { jobId, origin ->
                        appState.navigateToJobDetail(jobId, origin, backStackEntry)
                    })
            }
        }

    }
    @Composable
    fun AppHost(
        appState: AppState,
        onShowSnackBar: suspend (String, String?) -> Boolean,
        modifier: Modifier = Modifier,
    ) {

    }

    @Composable
    fun AppNavNavigate(
        modifier: Modifier,
        appState: AppState,
        startDestination: String,
    ) {
        val navController = appState.navController
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
            builder = {
                toScreen(
                    modifier = modifier,
                    navGraphBuilder = this,
                    appState = appState
                )
                JobDetailRoute.toScreen(navGraphBuilder = this,
                    onBackClick = {

                    },
                    onTopicClick = {

                    })
            })
    }
}