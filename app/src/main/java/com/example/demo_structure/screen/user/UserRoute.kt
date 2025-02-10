package com.example.demo_structure.screen.user

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.example.demo_structure.core.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavController.toUser(navOptions: NavOptions) =
    navigate(route = Destinations.Main.User.route, navOptions)

fun NavGraphBuilder.toMyProfileScreen(onNavigateToLogin: (String) -> Unit) {

    this.apply {
        composable(
            route = Destinations.Main.User.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "Google.com"
                }
            ),
            content = { navBackStackEntry ->
                UserScreen(
                    onNavigateToLogin = onNavigateToLogin,
                    onNavigateToProfile = {
                        Log.d("QQQ","Profile: $it")
                    },
                    userViewModel = koinViewModel<UserViewModel>()
                )
            }
        )
    }

}