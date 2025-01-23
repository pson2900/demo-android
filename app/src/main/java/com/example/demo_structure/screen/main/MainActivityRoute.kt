package com.example.demo_structure.screen.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navDeepLink
import com.example.demo_structure.app.manager.theme.AppIcons
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith
import kotlinx.serialization.Serializable

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
   /* HOME(
        selectedIcon = AppIcons.homeSelect,
        unselectedIcon = AppIcons.homeUnselect,
        idItem = "HomeTag",
        title = "Trang chủ",
        route = Destinations.HOME_ROUTE
    ),
    EDUCATION(
        selectedIcon = AppIcons.educationSelect,
        unselectedIcon = AppIcons.educationUnselect,
        idItem = "EducationTag",
        title = "Học tập",
        route = Destinations.EDUCATION_ROUTE
    ),
    OPPORTUNITY(
        selectedIcon = AppIcons.opportunitySelect,
        unselectedIcon = AppIcons.opportunityUnselect,
        idItem = "OpportunityTag",
        title = "Cơ hội",
        route = Destinations.OPPORTUNITY_ROUTE
    ),

    COMMUNITY(
        selectedIcon = AppIcons.communitySelect,
        unselectedIcon = AppIcons.communityUnselect,
        idItem = "CommunityTag",
        title = "Cộng đồng",
        route = Destinations.COMMUNITY_ROUTE
    ),

    USER(
        selectedIcon = AppIcons.userSelect,
        unselectedIcon = AppIcons.userUnselect,
        idItem = "UserTag",
        title = "Tôi",
        route = Destinations.USER_ROUTE
    ),*/
}

fun NavController.toMain(navOptions: NavOptions) =
    navigate(route = Destinations.Main.route, navOptions)


fun NavGraphBuilder.AppNavGraph(
    appState: AppState,
    topicDestination: NavGraphBuilder.() -> Unit,
) {
    composableWith(
        route = Destinations.Main.route,
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
                appState.navigateToJobDetail(jobId, origin, from = backStackEntry)
            },
            onNavigateToLogin = {
                appState.navigateToLogin(from = backStackEntry)
            }
        )
    }
    topicDestination()
}
