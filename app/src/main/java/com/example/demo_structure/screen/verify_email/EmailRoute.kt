package com.example.demo_structure.screen.verify_email

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.screen.create_pin.PinArguments
import com.example.demo_structure.screen.otp.OTPType
import org.koin.androidx.compose.koinViewModel


fun NavController.toVerifyEmail(navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = Destinations.Email.route, navOptions)

fun NavGraphBuilder.toVerifyEmailScreen(appState: AppState) {
    this.apply {
        composable(
            route = Destinations.Email.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                VerifyEmailScreen(
                    viewModel = koinViewModel(),
                    onNavigateToVerifyOtp = { email, type ->
                        appState.navigateToOTP(
                            from = navBackStackEntry,
                            email = email,
                            origin = type
                        )
                    },
                    onNavigateToLogin = { email ->
                        appState.navigateToLogin(
                            from = navBackStackEntry,
                            email = email
                        )
                    }
                )
            }
        )
    }
}

