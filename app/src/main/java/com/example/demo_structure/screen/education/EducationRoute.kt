package com.example.demo_structure.screen.education

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 20:36/1/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Serializable
object SearchResultRoute

fun NavController.navigateToEducation(navOptions: NavOptions) =
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


