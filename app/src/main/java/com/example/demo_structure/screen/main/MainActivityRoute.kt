package com.example.demo_structure.screen.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith
import com.example.demo_structure.screen.job_detail.toJobDetail
import com.example.demo_structure.screen.login.toLogin
import com.example.demo_structure.app.manager.theme.AppIcons

/**
 * Created by Phạm Sơn at 15:56/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

enum class MainDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val idItem: String,
    val title: String,
    val route: String,
) {
    HOME(
        selectedIcon = AppIcons.IconHomeSelect,
        unselectedIcon = AppIcons.IconHomeUnSelect,
        idItem = "HomeTag",
        title = "Trang chủ",
        route = Destinations.HOME_ROUTE
    ),
    EDUCATION(
        selectedIcon = AppIcons.IconEducationSelect,
        unselectedIcon = AppIcons.IconEducationUnSelect,
        idItem = "EducationTag",
        title = "Học tập",
        route = Destinations.EDUCATION_ROUTE
    ),
    OPPORTUNITY(
        selectedIcon = AppIcons.IconOpportunitySelect,
        unselectedIcon = AppIcons.IconOpportunityUnSelect,
        idItem = "OpportunityTag",
        title = "Cơ hội",
        route = Destinations.OPPORTUNITY_ROUTE
    ),

    COMMUNITY(
        selectedIcon = AppIcons.IconCommunitySelect,
        unselectedIcon = AppIcons.IconCommunityUnSelect,
        idItem = "CommunityTag",
        title = "Cộng đồng",
        route = Destinations.COMMUNITY_ROUTE
    ),

    USER(
        selectedIcon = AppIcons.IconUserSelect,
        unselectedIcon = AppIcons.IconUserUnSelect,
        idItem = "UserTag",
        title = "Tôi",
        route = Destinations.USER_ROUTE
    ),
}

fun NavController.toMain(navOptions: NavOptions) =
    navigate(route = Destinations.MAIN, navOptions)


fun NavGraphBuilder.AppNavGraph(
    appState: AppState,
    topicDestination: NavGraphBuilder.() -> Unit,
) {
    val navController = appState.navController
    composableWith(
        route = Destinations.MAIN,
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
