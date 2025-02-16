package com.example.demo_structure.screen.opportunity

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
) {
    this.apply {
        composable(
            route = Destinations.Main.Opportunity.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) },
            content = { navBackStackEntry ->

                OpportunityScreen(viewModel = koinViewModel(), onJobClick = {
                    onNavigateToJobDetail(it)
                })
            }
        )
    }
}


