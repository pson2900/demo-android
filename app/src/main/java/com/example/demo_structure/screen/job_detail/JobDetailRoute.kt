package com.example.demo_structure.screen.job_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith
import kotlinx.serialization.Serializable

/**
 * Created by Phạm Sơn at 23:47/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Serializable
object JobDetailRoute

fun NavController.navigateToJobDetail(navOptions: NavOptions) =
    navigate(
        route = "${Destinations.JOB_DETAIL_ROUTE}/" +
                "{${Destinations.JOB_DETAIL_ID_KEY}}" +
                "?origin={${Destinations.ORIGIN}}", navOptions
    )

fun NavGraphBuilder.JobDetailNavGraph(onBackClick: () -> Unit) {
//    navigation(
//        startDestination = Routes.MAIN_GRAPH,
//        route = Routes.APP_GRAPH
//    ) {

    composableWith(

        route = "${Destinations.JOB_DETAIL_ROUTE}/" +
                "{${Destinations.JOB_DETAIL_ID_KEY}}" +
                "?origin={${Destinations.ORIGIN}}",
//            route = Destinations.JOB_DETAIL_ROUTE,
        arguments = listOf(
            navArgument(Destinations.JOB_DETAIL_ID_KEY) {
                type = NavType.IntType
            }
        ),
        content = { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val jobId = arguments.getInt(Destinations.JOB_DETAIL_ID_KEY)
            val origin = arguments.getString(Destinations.ORIGIN)
            JobDetailScreen(
                jobId = 10,
                origin = "origin",
                onBackClick = onBackClick,
            )
        }
    )
//    }

}
