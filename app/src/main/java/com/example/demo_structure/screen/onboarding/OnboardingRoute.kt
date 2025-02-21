package com.example.demo_structure.screen.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations

fun NavController.toOnboarding(navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = Destinations.Onboarding.route, navOptions)

fun NavGraphBuilder.toOnboardingScreen(appState: AppState) {
    this.apply {
        composable(
            route = Destinations.Onboarding.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                OnboardingScreen {

                }
            }
        )
    }
}
