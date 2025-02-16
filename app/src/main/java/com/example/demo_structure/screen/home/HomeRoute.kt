package com.example.demo_structure.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import com.example.demo_structure.core.navigation.Destinations
import com.example.domain.model.JobDetail
import org.koin.androidx.compose.koinViewModel

fun NavController.toHome(navOptions: NavOptions = navOptions {}) =
    navigate(route = Destinations.Main.Home.route, navOptions)

fun NavGraphBuilder.toHomeScreen(
    onNavigateToJobDetail: (JobDetail,) -> Unit,
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
                    viewModel = koinViewModel(),
                    onNavigateToJobDetail = onNavigateToJobDetail,
                )
            }
        )
    }
}



