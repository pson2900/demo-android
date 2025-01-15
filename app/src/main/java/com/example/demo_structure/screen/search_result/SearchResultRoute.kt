package com.example.demo_structure.screen.search_result

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.screen.main.MainDestination
import kotlinx.serialization.Serializable

/**
 * Created by Phạm Sơn at 20:36/1/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Serializable
object SearchResultRoute

fun NavController.navigateToSearchResult(navOptions: NavOptions) =
    navigate(route = Destinations.SEARCH_ROUTE, navOptions)

fun NavGraphBuilder.toSearchResultScreen(
    nestedNavigation: AppState,
    onTopicClick: (String) -> Unit,
    modifier: Modifier,
) {
    this.apply {
        composable(
            route = Destinations.SEARCH_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = {
                SearchResultScreen(nestedNavigation,onTopicClick, modifier = modifier)
            }
        )
    }
}


