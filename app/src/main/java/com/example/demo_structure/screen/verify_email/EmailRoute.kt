package com.example.demo_structure.screen.verify_email

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations


fun NavController.toVerifyEmail(navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = Destinations.VERIFY_EMAIL, navOptions)

fun NavGraphBuilder.toVerifyEmailScreen() {
    this.apply {
        composable(
            route = Destinations.VERIFY_EMAIL,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                VerifyEmailRoute() {

                }
            }
        )
    }

}

