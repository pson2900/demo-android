package com.example.demo_structure.screen.user

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Phạm Sơn at 23:24/8/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Serializable
object UserRoute
fun NavController.navigateToUser(navOptions: NavOptions) =
    navigate(route = Destinations.USER_ROUTE, navOptions)


fun NavGraphBuilder.toUserScreen(nestedNavigation: AppState, onNavigateToLogin: () -> Unit, modifier: Modifier) {
    this.apply {
        composable(
            route = Destinations.USER_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                val userViewModel: UserViewModel = koinViewModel()
                val userState by userViewModel.menuUiState.collectAsStateWithLifecycle()
//                UserRoute(nestedNavigation,onLogin = onLogin, modifier = modifier)
                UserScreen(
                    nestedNavigation = nestedNavigation,
                    state = userState,
                    onNavigateToLogin = onNavigateToLogin,
                    userViewModel = userViewModel
                )
            }
        )
    }

}