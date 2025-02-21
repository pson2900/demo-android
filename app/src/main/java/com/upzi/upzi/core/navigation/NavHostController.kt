package com.upzi.upzi.core.navigation

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
import com.upzi.upzi.app.manager.theme.LocalNavAnimatedVisibilityScope

import com.upzi.upzi.screen.community.toCommunityScreen
import com.upzi.upzi.screen.create_pin.toCreatePinCodeScreen
import com.upzi.upzi.screen.education.toEducationScreen
import com.upzi.upzi.screen.home.toHomeScreen
import com.upzi.upzi.screen.job_detail.toJobDetail
import com.upzi.upzi.screen.job_detail.toJobDetailScreen
import com.upzi.upzi.screen.login.toLoginScreen
import com.upzi.upzi.screen.main.toMainScreen
import com.upzi.upzi.screen.onboarding.toOnboardingScreen
import com.upzi.upzi.screen.opportunity.toOpportunityScreen
import com.upzi.upzi.screen.otp.toVerifyOtpScreen
import com.upzi.upzi.screen.user.toMyProfileScreen
import com.upzi.upzi.screen.verify_email.toVerifyEmailScreen
import com.upzi.domain.model.JobDetail

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
    composable(
        route,
        arguments,
        deepLinks,
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition
    ) {
        CompositionLocalProvider(LocalNavAnimatedVisibilityScope provides this@composable) {
            content(it)
        }
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier,
    appState: AppState
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = Destinations.Main.ROUTE,
        modifier = modifier,
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },

        builder = {
//            CompositionLocalProvider(
//                LocalNavAnimatedContentScope provides this,
//            ){
//
//            }
            toMainScreen(appState = appState)
            /*toJobDetailScreen(appState = appState) {

            }*/
            toCreatePinCodeScreen(appState)
            toLoginScreen(appState) {
                appState.upPress()
            }
            toVerifyEmailScreen(appState = appState)
            toVerifyOtpScreen(appState)
            toOnboardingScreen(appState)
        })
}

@Composable
fun MainNavHost(
    appState: AppState,
    startDestination: DestinationItem,
    onNavigateToJobDetail: (JobDetail) -> Unit,
    onNavigateToLogin: (String) -> Unit,
    onNavigateToVerifyEmail: () -> Unit,
    onNavigateToOnBoarding: () -> Unit,
    onShowBottomNav: (Boolean) -> Unit,
    animatedVisibilityScope: AnimatedContentScope,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier,
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
    ) {
        toHomeScreen(
            onNavigateToJobDetail = onNavigateToJobDetail,
            onNavigateToVerifyEmail = onNavigateToVerifyEmail
        )
        toEducationScreen(
            onTopicClick = {},
            onNavigateToVerifyEmail = onNavigateToVerifyEmail,
            onNavigateToOnBoarding =onNavigateToOnBoarding
        )
        toOpportunityScreen(
            animatedVisibilityScope = animatedVisibilityScope,
            appState = appState,
            onNavigateToJobDetail = {
                onShowBottomNav.invoke(false)
                navController.toJobDetail(Destinations.JobDetail.createRoute(it))
            }
        )

        toJobDetailScreen(appState = appState, onLogin = onNavigateToLogin, onBackClick = {
            appState.upPress()
            onShowBottomNav.invoke(true)
        })
        toCommunityScreen(
            onTopicClick = {

            },
        )
        toMyProfileScreen(
            onNavigateToLogin = onNavigateToLogin,
        )
    }
}

