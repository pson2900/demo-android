package com.example.demo_structure.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.core.layout.WindowSizeClass
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.screen.community.CommunityNavGraph
import com.example.demo_structure.screen.education.EducationNavGraph
import com.example.demo_structure.screen.home.HomeNavGraph
import com.example.demo_structure.screen.job_detail.JobDetailNavGraph
import com.example.demo_structure.screen.login.toLoginScreen
import com.example.demo_structure.screen.main.AppNavGraph
import com.example.demo_structure.screen.opportunity.OpportunityNavGraph
import com.example.demo_structure.screen.otp.toVerifyOtpScreen
import com.example.demo_structure.screen.user.UserNavGraph
import com.example.demo_structure.screen.verify_email.toVerifyEmailScreen
import com.example.demo_structure.util.logNavigation

/**
 * Created by Phạm Sơn at 14:59/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */


fun NavGraphBuilder.composableWith(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        fadeIn()
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        fadeOut()
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(route, arguments, deepLinks, enterTransition, exitTransition, popEnterTransition, popExitTransition) {
        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this@composable) {
            content(it)
        }
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier,
    appState: AppState,
) {
    val navController = appState.navController
    logNavigation(navController)
    NavHost(
        navController = navController,
        startDestination = Destinations.Main.route,
        modifier = modifier,
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        builder = {
            AppNavGraph(
                appState = appState
            ) {

            }
            JobDetailNavGraph {

            }
            toLoginScreen() {
                appState.upPress()
            }
            toVerifyEmailScreen(appState)
            toVerifyOtpScreen()
        })
}

@Composable
fun MainNavHost(
    modifier: Modifier,
    windowSizeClass: WindowSizeClass,
    appState: AppState,
    onNavigateToJobDetail: (Int, String) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToVerifyEmail: () -> Unit,
) {
    appState.navController
    NavHost(
        navController = appState.navController,
        startDestination = Destinations.Main.Home.route,
        modifier = modifier,
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
    ) {
        HomeNavGraph(
            windowSizeClass = windowSizeClass,
            onNavigateToJobDetail = onNavigateToJobDetail,
        )
        EducationNavGraph(
            onTopicClick = {},
            onNavigateToVerifyEmail = onNavigateToVerifyEmail
        )
        OpportunityNavGraph(
            onTopicClick = {

            },
        )
        CommunityNavGraph(
            onTopicClick = {

            },
        )
        UserNavGraph(
            onNavigateToLogin = onNavigateToLogin,
        )
    }
}

