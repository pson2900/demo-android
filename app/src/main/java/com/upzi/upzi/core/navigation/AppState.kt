package com.upzi.upzi.core.navigation

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.util.trace
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.upzi.upzi.screen.create_pin.PinArguments
import com.upzi.upzi.screen.create_pin.toCreatePinCode
import com.upzi.upzi.screen.job_detail.toJobDetail
import com.upzi.upzi.screen.login.toLogin
import com.upzi.upzi.screen.main.toMain
import com.upzi.upzi.screen.onboarding.toOnboarding
import com.upzi.upzi.screen.otp.toVerifyOtp
import com.upzi.upzi.screen.verify_email.toVerifyEmail
import com.upzi.upzi.util.monitor.NetworkMonitor
import com.upzi.upzi.util.monitor.TimeZoneMonitor
import com.upzi.domain.model.JobDetail
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone
import org.koin.compose.koinInject

/**
 * Created by Phạm Sơn at 10:55/10/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
/**
 * Remember and creates an instance of [ScaffoldState]
 */
@Composable
fun rememberAppState(
    networkMonitor: NetworkMonitor = koinInject(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current,
    timeZoneMonitor: TimeZoneMonitor = koinInject(),
): AppState = remember(key1 = navController, key2 = coroutineScope, calculation = {
    AppState(
        navController = navController,
        coroutineScope = coroutineScope,
        networkMonitor = networkMonitor,
        context = context,
        timeZoneMonitor = timeZoneMonitor,
    )
})

@Stable
class AppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    val networkMonitor: NetworkMonitor,
    var context: Context,
    val timeZoneMonitor: TimeZoneMonitor,
) {
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    var isOnline by mutableStateOf(checkIfOnline())
        private set

    fun refreshOnline() {
        isOnline = checkIfOnline()
    }


    @Suppress("DEPRECATION")
    private fun checkIfOnline(): Boolean {
        val cm = getSystemService(context, ConnectivityManager::class.java)
        return cm?.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    val currentTimeZone = timeZoneMonitor.currentTimeZone
        .stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5_000),
            TimeZone.currentSystemDefault(),
        )

    val currentTime = timeZoneMonitor.currentTime
        .stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5_000),
            TimeZone.currentSystemDefault(),
        )

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        trace("Navigation: ${route}") {
            if (route != navController.currentDestination?.route) {
                navController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true
                    // Pop up backstack to the first destination and save state. This makes going back
                    // to the start destination when pressing back in any other bottom tab.
                    popUpTo(findStartDestination(navController.graph).id) {
                        saveState = true
                    }
                }
            }
        }
    }

    fun navigateToJobDetail(jobId: Int, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val route = "${Destinations.JobDetail.route}/$jobId"
        trace("Navigation : ${route}") {
            if (from.lifecycleIsResumed()) {
                navController.toJobDetail(
                    Destinations.JobDetail.createRoute(
                        jobId.toString(),
                    )
                )
            }
        }
    }

    fun navigateToJobDetail(jobDetail: JobDetail, from: NavBackStackEntry) {
        val route = "${Destinations.JobDetail.route}/${jobDetail.jobId}"
        trace("Navigation : $route") {
            if (from.lifecycleIsResumed()) {
                navController.toJobDetail(Destinations.JobDetail.createRoute(jobDetail))
            }
        }
    }

    fun navigateToLogin(from: NavBackStackEntry, email: String) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val route = Destinations.Login.route
        trace("Navigation : ${route}") {
            if (from.lifecycleIsResumed()) {
                navController.toLogin(
                    Destinations.Login.createRoute(
                        email = email
                    )
                )
            }
        }
    }


    fun navigateToMain(from: NavBackStackEntry, startDestinations: DestinationItem) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val route = Destinations.Main.createRoute(
            startDestinations
        )
        Log.d("QQQ", "navigateToMain startDestinations: ${route}")
        trace("Navigation : ${route}") {
            if (from.lifecycleIsResumed()) {
                navController.toMain(route)

            }
        }
    }

    fun navigateToEmail(from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val route = Destinations.Email.route
        trace("Navigation : $route") {
            if (from.lifecycleIsResumed()) {
                navController.toVerifyEmail()
            }
        }
    }

    fun navigateToOTP(from: NavBackStackEntry, email: String, origin: String) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val route = "${Destinations.OTP.route}/$email?origin=$origin"
        trace("Navigation : $route") {
            if (from.lifecycleIsResumed()) {
                navController.toVerifyOtp(
                    Destinations.OTP.createRoute(
                        email = email,
                        origin = origin
                    )
                )
            }
        }
    }

    fun navigateToPinCode(from: NavBackStackEntry, pinArguments: PinArguments) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val pinJson = Gson().toJson(pinArguments)
        val route = "${Destinations.CreatePin.route}/$pinJson"

        trace("Navigation : $route") {
            if (from.lifecycleIsResumed()) {
                navController.toCreatePinCode(
                    Destinations.CreatePin.createRoute(pinJson = pinJson)
                )
            }
        }
    }

    fun navigateToOnBoarding(from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val route = "${Destinations.Onboarding.route}"
        trace("Navigation : $route") {
            if (from.lifecycleIsResumed()) {
                navController.toOnboarding()
            }
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
