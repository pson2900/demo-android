package com.upzi.upzi.screen.opportunity

import androidx.compose.animation.AnimatedContentScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navDeepLink
import com.upzi.upzi.core.navigation.AppState
import com.upzi.upzi.core.navigation.Destinations
import com.upzi.upzi.core.navigation.composableWith
import com.upzi.domain.model.JobDetail
import org.koin.androidx.compose.koinViewModel

fun NavController.toOpportunity(navOptions: NavOptions) =
    navigate(route = Destinations.Main.Opportunity.route, navOptions)

fun NavGraphBuilder.toOpportunityScreen(
    appState: AppState,
    onNavigateToJobDetail: (JobDetail) -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
) {
    this.apply {
        composableWith(
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


