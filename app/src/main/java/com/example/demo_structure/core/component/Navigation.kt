package com.example.demo_structure.core.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.demo_structure.theme.ProductXTheme
import com.example.demo_structure.theme.toIcon
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.screen.main.MainDestination

/**
 * Created by Phạm Sơn at 16:24/10/1/25
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
        CompositionLocalProvider(
//            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            content(it)
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    appState: AppState,
    containerColor: Color = ProductXTheme.colors.surface,
    contentColor: Color = ProductXTheme.colors.onSurface,
    tonalElevation: Dp = 0.dp,
    windowInsets: WindowInsets = WindowInsets.navigationBars
) {
    val currentRoute = appState.navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        modifier = modifier,
        tonalElevation = tonalElevation,
        windowInsets = windowInsets,
        containerColor = containerColor, // Use the surface color from theme
        contentColor = contentColor // Text and icon color from theme
    ) {
        MainDestination.entries.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    appState.navigateToBottomBarRoute(item.route)
                },
                label = {
                    Text(
                        text = item.name,
//                        style = ProductXTheme.typography.body2.copy(fontSize = 10.sp)
                    )
                },
                icon = {
                    val icon = if (currentRoute == item.route) {
                        item.selectedIcon.toIcon()
                    } else {
                        item.unselectedIcon.toIcon()
                    }
                },
                alwaysShowLabel = false, // Consider showing labels only on selected items for better UX
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
