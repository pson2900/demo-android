package com.example.demo_structure.screen.opportunity

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.screen.main.MainDestination
import org.koin.androidx.compose.koinViewModel

fun NavController.toOpportunity(navOptions: NavOptions) =
    navigate(route = MainDestination.OPPORTUNITY.route, navOptions)

fun NavGraphBuilder.OpportunityNavGraph(
    onTopicClick: (String) -> Unit,
) {
    this.apply {
        composable(
            route = MainDestination.OPPORTUNITY.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = {
                OpportunityScreen(viewModel = koinViewModel(), onTopicClick)
            }
        )
    }
}


