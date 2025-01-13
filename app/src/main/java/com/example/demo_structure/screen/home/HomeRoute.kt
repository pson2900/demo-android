package com.example.demo_structure.screen.home

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.screen.main.MainDestination
import kotlinx.serialization.Serializable

/**
 * Created by Phạm Sơn at 15:17/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Serializable
object HomeRoute {

    fun toScreen(
        navGraphBuilder: NavGraphBuilder,
        onTopicClick: (String) -> Unit,
        modifier: Modifier,
        onSnackSelected: (Int, String) -> Unit,
    ) {
        navGraphBuilder.apply {
            composable(
                route = MainDestination.HOME.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "Google.com"
                    }
                ),
                content = { navBackStackEntry ->
                    HomeRoute(onTopicClick, onSnackSelected, modifier)
                }
            )
        }
    }
}




