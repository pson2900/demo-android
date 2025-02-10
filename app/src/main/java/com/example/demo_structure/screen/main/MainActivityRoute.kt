package com.example.demo_structure.screen.main

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith

fun NavController.toMain(route: String, navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = route, navOptions = navOptions)

fun NavGraphBuilder.toMainScreen(
    appState: AppState,
) {
    composableWith(
        route = Destinations.Main.route + "?tab={tab}",
        arguments = listOf(
            navArgument(Destinations.Main.Tab) {
                type = NavType.StringType
                defaultValue = Destinations.Main.Home.route
                nullable = true
            }),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://staging.vietnamworks.com/?tab={tab}"
            },
        ),
        content = { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val selectedTabRoute = arguments.getString(Destinations.Main.Tab)
            Log.d("QQQ", "selectedTabRoute: $selectedTabRoute")
            val selectedTab = when (selectedTabRoute) {
                Destinations.Main.Home.route -> Destinations.Main.Home
                Destinations.Main.Education.route -> Destinations.Main.Education
                Destinations.Main.Opportunity.route -> Destinations.Main.Opportunity
                Destinations.Main.Community.route -> Destinations.Main.Community
                Destinations.Main.User.route -> Destinations.Main.User
                else -> Destinations.Main.Home
            }
            Log.d("QQQ", "selectedTab: $selectedTab")
            MainContent(
                startDestination = selectedTab,
                onNavigateToJobDetail = { jobId, origin ->
                    appState.navigateToJobDetail(jobId, origin, from = backStackEntry)
                },
                onNavigateToLogin = { email ->
                    appState.navigateToLogin(from = backStackEntry, email = email)
                },
                onNavigateToVerifyEmail = {
                    appState.navigateToEmail(from = backStackEntry)
                }
            )
        }
    )
}
