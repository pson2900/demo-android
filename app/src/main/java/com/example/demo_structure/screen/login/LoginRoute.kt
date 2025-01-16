package com.example.demo_structure.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations

fun NavController.navigateToLogin(navOptions: NavOptions) =
    navigate(route = Destinations.LOGIN_ROUTE, navOptions)

fun NavGraphBuilder.toLoginScreen() {
    this.apply {
        composable(
            route = Destinations.LOGIN_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                LoginRoute() {

                }
            }
        )
    }

}


