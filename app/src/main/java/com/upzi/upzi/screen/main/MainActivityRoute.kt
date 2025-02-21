package com.upzi.upzi.screen.main

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.upzi.upzi.core.navigation.AppState
import com.upzi.upzi.core.navigation.Destinations
import com.upzi.upzi.core.navigation.composableWith

fun NavController.toMain(route: String, navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = route, navOptions = navOptions)

fun NavGraphBuilder.toMainScreen(
    appState: AppState,
) {
    Log.d("QQQ", "toMainScreen")
    composableWith(
        route = Destinations.Main.ROUTE + "?tab={tab}",
        arguments = listOf(
            navArgument(Destinations.Main.TAB) {
                type = NavType.StringType
                defaultValue = Destinations.Main.Home.route
                nullable = true
            }),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "https://staging.vietnamworks.com/?tab={tab}"
            },
        ),
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val selectedTabRoute = arguments.getString(Destinations.Main.TAB)
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
            animatedVisibilityScope = this,
            startDestination = selectedTab,
            onNavigateToJobDetail = { jobDetail ->
                appState.navigateToJobDetail(jobDetail, from = backStackEntry)
            },
            onNavigateToLogin = { email ->
                appState.navigateToLogin(from = backStackEntry, email = email)
            },
            onNavigateToVerifyEmail = {
                appState.navigateToEmail(from = backStackEntry)
            }, onNavigateToOnBoarding = {
                appState.navigateToOnBoarding(from = backStackEntry)
            }
        )
    }
}
