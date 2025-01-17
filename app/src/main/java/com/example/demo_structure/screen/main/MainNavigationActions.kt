package com.example.demo_structure.screen.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith
import com.example.demo_structure.screen.job_detail.toJobDetail
import com.example.demo_structure.screen.login.toLogin

/**
 * Created by Phạm Sơn at 16:37/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */


fun NavGraphBuilder.AppNavGraph(
    appState: AppState,
    topicDestination: NavGraphBuilder.() -> Unit,
) {
    val navController = appState.navController
    composableWith(
        route = Destinations.APP,
        deepLinks = listOf(
            navDeepLink {
                /**
                 * This destination has a deep link that enables a specific news resource to be
                 * opened from a notification (@see SystemTrayNotifier for more). The news resource
                 * ID is sent in the URI rather than being modelled in the route type because it's
                 * transient data (stored in SavedStateHandle) that is cleared after the user has
                 * opened the news resource.
                 */
                /**
                 * This destination has a deep link that enables a specific news resource to be
                 * opened from a notification (@see SystemTrayNotifier for more). The news resource
                 * ID is sent in the URI rather than being modelled in the route type because it's
                 * transient data (stored in SavedStateHandle) that is cleared after the user has
                 * opened the news resource.
                 */
                uriPattern = "DEEP_LINK_URI_PATTERN"
            },
        ),
    ) { backStackEntry ->
        MainContent(
            onNavigateToJobDetail = { jobId, origin ->
                navController.toJobDetail(jobId, origin)
            },
            onNavigateToLogin = {
                navController.toLogin()
            }
        )
    }
    topicDestination()
}
