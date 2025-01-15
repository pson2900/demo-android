package com.example.demo_structure.screen.login

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import kotlinx.serialization.Serializable

/**
 * Created by Phạm Sơn at 23:24/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Serializable
object LoginRoute

fun NavController.navigateToLogin(navOptions: NavOptions) =
    navigate(route = Destinations.LOGIN_ROUTE, navOptions)

fun NavGraphBuilder.toLoginScreen(modifier: Modifier) {
    this.apply {
        composable(
            route = Destinations.LOGIN_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                LoginRoute {

                }
            }
        )
    }

}


