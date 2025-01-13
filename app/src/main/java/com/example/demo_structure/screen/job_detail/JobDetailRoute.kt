package com.example.demo_structure.screen.job_detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.demo_structure.core.component.composableWith
import com.example.demo_structure.screen.main.Destinations
import kotlinx.serialization.Serializable

/**
 * Created by Phạm Sơn at 23:47/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Serializable
object JobDetailRoute {
    fun toScreen(
        navGraphBuilder: NavGraphBuilder,
        onBackClick: () -> Unit,
        onTopicClick: (String) -> Unit,
    ) {
        navGraphBuilder.apply {
            composableWith(
                route = "${Destinations.JOB_DETAIL_ROUTE}/" +
                        "{${Destinations.JOB_DETAIL_ID_KEY}}" +
                        "?origin={${Destinations.ORIGIN}}",
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
                        jobId = jobId,
                        origin = "origin",
                        onBackClick = onBackClick,
                        onTopicClick = onTopicClick
                    )
                }
            )
        }

    }

}
