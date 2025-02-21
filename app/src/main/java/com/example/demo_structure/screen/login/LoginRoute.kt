package com.example.demo_structure.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.screen.otp.OTPType
import org.koin.androidx.compose.koinViewModel

fun NavController.toLogin(route: String, navOptions: NavOptions = navOptions {}) =
    navigate(route = route, navOptions)

fun NavGraphBuilder.toLoginScreen(appState: AppState, onBackClick: () -> Unit) {
    this.apply {
        composable(
            route = "${Destinations.Login.route}/" +
                    "{${Destinations.Login.EMAIL}}",
            arguments = listOf(
                navArgument(Destinations.Login.EMAIL) {
                    type = NavType.StringType
                }
            ),
            content = { navBackStackEntry ->
                val arguments = requireNotNull(navBackStackEntry.arguments)
                val email = arguments.getString(Destinations.Login.EMAIL) ?: ""
                LoginScreen( viewModel = koinViewModel(), email = email, onNavigateForgotPasswordOtp = {
                    appState.navigateToOTP(
                        from = navBackStackEntry,
                        email = email,
                        origin = OTPType.FORGOT_PASSWORD.type
                    )
                }, onNavigateHomeScreen = {
                    Destinations.Main.getEntries().find { it.testTag == "HomeTag" }?.let { item->
                        appState.navigateToMain(navBackStackEntry, Destinations.Main.Home)
                    }
                }, onNavigateOnBoarding = {
                    appState.navigateToOnBoarding(navBackStackEntry)
                },
                    onBack = {
                    appState.upPress()
                })
            }
        )
    }
}


