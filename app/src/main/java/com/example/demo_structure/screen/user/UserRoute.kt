package com.example.demo_structure.screen.user

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.screen.main.MainDestination
import org.koin.androidx.compose.koinViewModel

fun NavController.toUser(navOptions: NavOptions) =
    navigate(route =  MainDestination.USER.route, navOptions)

fun NavGraphBuilder.UserNavGraph(onNavigateToLogin: () -> Unit) {
    this.apply {
        composable(
            route = MainDestination.USER.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                UserScreen(
                    onNavigateToLogin = onNavigateToLogin,
                    userViewModel = koinViewModel()
                )
            }
        )
    }

}