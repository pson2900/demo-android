package com.example.demo_structure.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(route = Destinations.HOME_ROUTE, navOptions)

fun NavGraphBuilder.HomeNavGraph(
    onNavigateToJobDetail: (Int, String) -> Unit,
) {
    this.apply {
        composable(
            route = Destinations.HOME_ROUTE,
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



