package com.example.demo_structure.screen.otp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavController.toVerifyOtp(route: String, navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = route, navOptions)

fun NavGraphBuilder.toVerifyOtpScreen(appState: AppState) {
    this.apply {
        composable(
            route = "${Destinations.OTP.route}/" +
                    "{${Destinations.OTP.EMAIL}}" +
                    "?origin={${Destinations.OTP.ORIGIN}}",

            arguments = listOf(
                navArgument(Destinations.OTP.EMAIL) {
                    type = NavType.StringType
                },
                navArgument(Destinations.OTP.ORIGIN) {
                    type = NavType.StringType
                    nullable = true
                }
            ),
            content = { navBackStackEntry ->
                val arguments = requireNotNull(navBackStackEntry.arguments)
                val email = arguments.getString(Destinations.OTP.EMAIL) ?: ""
                val origin = arguments.getString(Destinations.OTP.ORIGIN) ?: ""
                VerifyOTPScreen(viewModel = koinViewModel(), email = email,
                    origin = origin,
                    onNavigatePinCode = { arg,origin->
                        appState.navigateToPinCode(navBackStackEntry,arg,origin)
                })
            }
        )
    }
}
