package com.example.demo_structure.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.window.core.layout.WindowSizeClass
import com.example.demo_structure.core.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavController.toHome(navOptions: NavOptions) =
    navigate(route = Destinations.Main.Home.route, navOptions)

fun NavGraphBuilder.HomeNavGraph(
    windowSizeClass: WindowSizeClass,
    onNavigateToJobDetail: (Int, String) -> Unit,
) {
    this.apply {
        composable(
            route = Destinations.Main.Home.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                HomeScreen(
                    windowSizeClass = windowSizeClass,
                    viewModel = koinViewModel(),
                    onNavigateToJobDetail = onNavigateToJobDetail,
                )
            }
        )
    }
}



