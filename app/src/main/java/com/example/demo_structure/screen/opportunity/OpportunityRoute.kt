package com.example.demo_structure.screen.opportunity

import androidx.compose.animation.AnimatedContentScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.domain.model.JobDetail
import org.koin.androidx.compose.koinViewModel

fun NavController.toOpportunity(navOptions: NavOptions) =
    navigate(route = Destinations.Main.Opportunity.route, navOptions)

fun NavGraphBuilder.toOpportunityScreen(
    appState: AppState,
    onNavigateToJobDetail: (JobDetail) -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
) {
    this.apply {
        composable(
            route = Destinations.Main.Opportunity.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->

                OpportunityScreen(animationContentScope = this,viewModel = koinViewModel(), onJobClick = {
                    onNavigateToJobDetail(it)
                })
            }
        )
    }
}


