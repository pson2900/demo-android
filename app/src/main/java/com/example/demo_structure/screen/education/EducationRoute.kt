package com.example.demo_structure.screen.education

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavController.toEducation(navOptions: NavOptions) =
    navigate(route = Destinations.EDUCATION_ROUTE, navOptions)

fun NavGraphBuilder.EducationNavGraph(
    onTopicClick: (String) -> Unit,
) {
    this.apply {
        composable(
            route = Destinations.EDUCATION_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = {
                EducationScreen(viewModel = koinViewModel(), onTopicClick)
            }
        )
    }
}


