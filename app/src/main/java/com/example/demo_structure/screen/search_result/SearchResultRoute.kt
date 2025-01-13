package com.example.demo_structure.screen.search_result

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.screen.main.MainDestination
import kotlinx.serialization.Serializable

/**
 * Created by Phạm Sơn at 20:36/1/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Serializable
object SearchResultRoute {
    fun toScreen(
        navGraphBuilder: NavGraphBuilder,
        onTopicClick: (String) -> Unit,
        modifier: Modifier,
    ) {
        navGraphBuilder.apply {
            composable(
                route = MainDestination.SEARCH.route,
                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "Google.com"
                    }
                ),
                content = {
                    SearchResultScreen(onTopicClick, modifier = modifier)
                }
            )
        }
    }
}


