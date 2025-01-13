package com.example.demo_structure.screen.user

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.screen.main.MainDestination
import kotlinx.serialization.Serializable

/**
 * Created by Phạm Sơn at 23:24/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Serializable
object UserRoute {
    fun toScreen(navGraphBuilder: NavGraphBuilder, onTopicClick: (String) -> Unit, modifier: Modifier) {
        navGraphBuilder.apply {
            composable(
                route = MainDestination.USER.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "Google.com"
                    }
                ),
                content = { navBackStackEntry ->
                    UserRoute(onTopicClick = onTopicClick, modifier = modifier)
                }
            )
        }

    }
}


