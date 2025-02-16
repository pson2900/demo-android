package com.example.demo_structure.screen.job_detail

import android.os.Build
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
//            navArgument(Destinations.JobDetail.JOB_DETAIL_ID) {
//                type = NavType.IntType
//            },

            navArgument(Destinations.JobDetail.JOB_DETAIL_ID) {
                type = NavType.StringType
            },


        ),
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) },
        content = { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val parseJobDetail = arguments.getString(Destinations.JobDetail.JOB_DETAIL_ID)
            val job = Gson().fromJson(parseJobDetail, JobDetail::class.java)
//            val jobId = arguments.getInt(Destinations.JobDetail.JOB_DETAIL_ID)

            /* JobDetailScreen(
                 jobId = jobId,
                 onBackClick = {
                     appState.navigateToMain(navBackStackEntry, Destinations.Main.Community)
                 },
                 viewModel = koinViewModel()
             )*/
            job?.let {
                JobDetailScreen1(

                    job = it,
                    onBack = {
                        appState.navigateToMain(navBackStackEntry, Destinations.Main.Community)
                    },
                )
            }
        }
    )
}
