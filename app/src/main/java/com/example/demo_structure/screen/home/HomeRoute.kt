package com.example.demo_structure.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Serializable
object HomeRoute

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



