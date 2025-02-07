package com.example.demo_structure.screen.create_pin

import android.os.Build
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

fun NavController.toCreatePinCode(route: String, navOptions: NavOptions = androidx.navigation.navOptions {}) =
    navigate(route = route, navOptions)

fun NavGraphBuilder.toCreatePinCodeScreen(appState: AppState) {
    this.apply {
        composable(
            route = "${Destinations.CreatePin.route}/{${Destinations.CreatePin.JSON}}?origin={${Destinations.OTP.ORIGIN}}",
            arguments = listOf(
                navArgument(Destinations.CreatePin.JSON) {
                    type = NavType.StringType
                },
                navArgument(Destinations.CreatePin.ORIGIN) {
                    type = NavType.StringType
                    nullable = true
                }
            ),
            content = { navBackStackEntry ->
                val arguments = requireNotNull(navBackStackEntry.arguments)
                val pinJson = arguments.getString(Destinations.CreatePin.JSON)
                val pin: PinArguments? = if (!pinJson.isNullOrEmpty()) {
                    Gson().fromJson(pinJson, PinArguments::class.java)
                } else {
                    null
                }
                val origin = arguments.getString(Destinations.CreatePin.ORIGIN)?:""
                PinCodeScreen(viewModel = koinViewModel(), arguments = pin, origin = origin)
            }
        )
    }
}