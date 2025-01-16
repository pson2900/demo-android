package com.example.demo_structure.screen.user

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 23:24/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Serializable
object UserRoute

fun NavController.navigateToUser(navOptions: NavOptions) =
    navigate(route = Destinations.USER_ROUTE, navOptions)


fun NavGraphBuilder.UserNavGraph(onNavigateToLogin: () -> Unit) {
    this.apply {
        composable(
            route = Destinations.USER_ROUTE,
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