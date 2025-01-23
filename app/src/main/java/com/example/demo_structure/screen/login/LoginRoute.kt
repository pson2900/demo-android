package com.example.demo_structure.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import com.example.demo_structure.core.navigation.Destinations

fun NavController.toLogin(navOptions: NavOptions = navOptions {}) =
    navigate(route = Destinations.Login.route, navOptions)

fun NavGraphBuilder.toLoginScreen(onBackClick: () -> Unit) {
    this.apply {
        composable(
            route = Destinations.Login.route,
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


