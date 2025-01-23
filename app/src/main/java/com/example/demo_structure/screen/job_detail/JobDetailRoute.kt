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
        route = "${Destinations.JobDetail.route}/" +
                "$jobId" +
                "?origin=${origin}", navOptions
    )

fun NavGraphBuilder.JobDetailNavGraph(onBackClick: () -> Unit) {
    composableWith(
        route = "${Destinations.JobDetail.route}/" +
                "{${Destinations.JobDetail.JOB_DETAIL_ID}}" +
                "?origin={${Destinations.JobDetail.ORIGIN}}",
        arguments = listOf(
            navArgument(Destinations.JobDetail.JOB_DETAIL_ID) {
                type = NavType.IntType
            },
            navArgument(Destinations.JobDetail.ORIGIN) {
                type = NavType.StringType
                nullable = true
            }
        ),
        content = { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val jobId = arguments.getInt(Destinations.JobDetail.JOB_DETAIL_ID)
            val origin = arguments.getString(Destinations.JobDetail.ORIGIN)
            JobDetailScreen(
                jobId = jobId,
                origin = origin ?: "",
                onBackClick = onBackClick,
                viewModel = koinViewModel()
            )
        }
    )
}
