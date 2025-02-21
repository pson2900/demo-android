package com.upzi.upzi.screen.education

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.upzi.upzi.core.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavController.toEducation(navOptions: NavOptions) =
    navigate(route = Destinations.Main.Education.route, navOptions)

fun NavGraphBuilder.toEducationScreen(
    onNavigateToVerifyEmail: () -> Unit,
    onNavigateToOnBoarding: () -> Unit,
    onTopicClick: (String) -> Unit,
) {

    this.apply {
        composable(
            route = Destinations.Main.Education.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = {
                EducationScreen(
                    viewModel = koinViewModel(),
                    onNavigateToVerifyEmail = onNavigateToVerifyEmail,
                    onNavigateToOnBoarding = onNavigateToOnBoarding,
                    onTopicClick = onTopicClick
                )
            }
        )
    }
}


