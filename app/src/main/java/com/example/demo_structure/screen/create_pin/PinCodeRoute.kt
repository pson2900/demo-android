package com.example.demo_structure.screen.create_pin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.screen.verify_email.VerifyEmailScreen
import org.koin.androidx.compose.koinViewModel

fun NavController.toCreatePinCode(navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = Destinations.CreatePin.route, navOptions)

fun NavGraphBuilder.toCreatePinCodeScreen(appState: AppState) {
    this.apply {
        composable(
            route = Destinations.CreatePin.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                VerifyEmailScreen(
                    viewModel = koinViewModel(),
                    onNavigateToVerifyOtp = { email, type -> appState.navigateToOTP(from = navBackStackEntry, email = email, origin = type) },
                    onNavigateToLogin = { appState.navigateToLogin(from = navBackStackEntry) }
                )
            }
        )
    }
}
