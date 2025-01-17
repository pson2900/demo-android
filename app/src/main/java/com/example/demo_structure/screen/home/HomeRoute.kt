package com.example.demo_structure.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.screen.main.MainDestination
import org.koin.androidx.compose.koinViewModel

fun NavController.toHome(navOptions: NavOptions) =
    navigate(route = MainDestination.HOME.route, navOptions)

fun NavGraphBuilder.HomeNavGraph(
    onNavigateToJobDetail: (Int, String) -> Unit,
) {
    this.apply {
        composable(
            route = MainDestination.HOME.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                HomeScreen(
                    viewModel = koinViewModel(),
                    onNavigateToJobDetail = onNavigateToJobDetail,
                )
            }
        )
    }
}



