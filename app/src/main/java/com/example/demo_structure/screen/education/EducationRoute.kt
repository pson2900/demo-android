package com.example.demo_structure.screen.education

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.screen.main.MainDestination
import org.koin.androidx.compose.koinViewModel

fun NavController.toEducation(navOptions: NavOptions) =
    navigate(route = MainDestination.EDUCATION.route, navOptions)

fun NavGraphBuilder.EducationNavGraph(
    onNavigateToVerifyEmail: () -> Unit,
    onTopicClick: (String) -> Unit,
) {

    this.apply {
        composable(
            route = MainDestination.EDUCATION.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = {
                EducationScreen(viewModel = koinViewModel(), onTopicClick, onNavigateToVerifyEmail = onNavigateToVerifyEmail)
            }
        )
    }
}


