package com.example.demo_structure.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.trace
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.demo_structure.util.NetworkMonitor
import com.example.demo_structure.screen.main.Destinations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AppState = remember(key1 = navController, key2 = coroutineScope, calculation = {
    AppState(
        navController = navController,
        coroutineScope = coroutineScope,
        networkMonitor = networkMonitor,
//            userNewsResourceRepository = userNewsResourceRepository,
//            timeZoneMonitor = timeZoneMonitor,
    )
})

@Stable
class AppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
) {
    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
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

    fun navigateToJobDetail(jobId: Int, origin: String, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val route = "${Destinations.JOB_DETAIL_ROUTE}/$jobId?origin=$origin"
        trace("Navigation : ${route}") {
            if (from.lifecycleIsResumed()) {
                navController.navigate(route = route)
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
