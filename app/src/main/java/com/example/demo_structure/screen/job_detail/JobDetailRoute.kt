package com.example.demo_structure.screen.job_detail

import android.os.Build
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.composableWith
import com.example.domain.model.JobDetail
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

fun NavController.toJobDetail(route: String, navOptions: NavOptions = androidx.navigation.navOptions { }) =
    navigate(route = route, navOptions = navOptions)


fun NavGraphBuilder.toJobDetailScreen(appState: AppState, onBackClick: () -> Unit) {
    composableWith(
        route = "${Destinations.JobDetail.route}/" +
                "{${Destinations.JobDetail.JOB_DETAIL_ID}}",
        arguments = listOf(
            navArgument(Destinations.JobDetail.JOB_DETAIL_ID) {
                type = NavType.StringType
            },

        ),
        content = { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val jobDetailJson = arguments.getString(Destinations.JobDetail.JOB_DETAIL_ID)
            val jobDetail = remember { Gson().fromJson(jobDetailJson, JobDetail::class.java) }
            jobDetail?.let {
                JobDetailScreen1(
                    job = it,
                    onBackClick = {
                        appState.navigateToMain(navBackStackEntry, Destinations.Main.Opportunity)
                    },
                )
            }
        }
    )
}
