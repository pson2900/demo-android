package com.example.demo_structure.screen.community

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavController.navigateToCommunity(navOptions: NavOptions) =
    navigate(route = Destinations.COMMUNITY_ROUTE, navOptions)

fun NavGraphBuilder.CommunityNavGraph(
    onTopicClick: (String) -> Unit,
) {
    this.apply {
        composable(
            route = Destinations.COMMUNITY_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = {
                CommunityScreen(viewModel = koinViewModel(), onTopicClick)
            }
        )
    }
}


