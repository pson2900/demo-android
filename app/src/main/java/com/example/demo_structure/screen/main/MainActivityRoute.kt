package com.example.demo_structure.screen.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith

fun NavController.toMain(navOptions: NavOptions) =
    navigate(route = Destinations.Main.route, navOptions)

fun NavGraphBuilder.toMainScreen(
    appState: AppState,
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
            onNavigateToLogin = {email->
                appState.navigateToLogin(from = backStackEntry, email = email)
            },
            onNavigateToVerifyEmail = {
                appState.navigateToEmail(from = backStackEntry)
            }
        )
    }
}
