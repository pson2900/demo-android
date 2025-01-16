package com.example.demo_structure.screen.job_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith
import org.koin.androidx.compose.koinViewModel

fun NavController.toJobDetail(jobId: Int, origin: String, navOptions: NavOptions = androidx.navigation.navOptions { }) =
    navigate(
        route = "${Destinations.JOB_DETAIL_ROUTE}/" +
                "$jobId" +
                "?origin=${origin}", navOptions
    )

fun NavGraphBuilder.JobDetailNavGraph(onBackClick: () -> Unit) {
    composableWith(
        route = "${Destinations.JOB_DETAIL_ROUTE}/" +
                "{${Destinations.JOB_DETAIL_ID_KEY}}" +
                "?origin={${Destinations.ORIGIN}}",
        arguments = listOf(
            navArgument(Destinations.JOB_DETAIL_ID_KEY) {
                type = NavType.IntType
            },
            navArgument(Destinations.ORIGIN) {
                type = NavType.StringType
                nullable = true
            }
        ),
        content = { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val jobId = arguments.getInt(Destinations.JOB_DETAIL_ID_KEY)
            val origin = arguments.getString(Destinations.ORIGIN)
            JobDetailScreen(
                jobId = jobId,
                origin = origin ?: "",
                onBackClick = onBackClick,
                viewModel = koinViewModel()
            )
        }
    )
}
