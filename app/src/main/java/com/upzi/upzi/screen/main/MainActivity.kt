package com.upzi.upzi.screen.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.trace
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import com.upzi.upzi.app.InitializeApp
import com.upzi.upzi.app.manager.theme.LocalSharedTransitionScope
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppScaffold
import com.upzi.upzi.core.component.AppSnackBar
import com.upzi.upzi.core.component.BottomNavigationBar
import com.upzi.upzi.core.component.InitBottomMainScreen
import com.upzi.upzi.core.navigation.DestinationItem
import com.upzi.upzi.core.navigation.MainNavHost
import com.upzi.upzi.core.navigation.rememberAppState
import com.upzi.upzi.screen.job_detail.nonSpatialExpressiveSpring
import com.upzi.upzi.screen.job_detail.spatialExpressiveSpring
import com.upzi.upzi.util.extension.isSystemInDarkTheme
import com.upzi.upzi.util.monitor.NetworkMonitor
import com.upzi.domain.model.JobDetail
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = Color.White

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = Color.Black

/**
 * Class for the system theme settings.
 * This wrapping class allows us to combine all the changes and prevent unnecessary recompositions.
 */


data class ThemeSettings(
    val darkTheme: Boolean,
    val androidTheme: Boolean,
    val disableDynamicTheming: Boolean,
)

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        observeThemeSettings()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setupSplashScreen(splashScreen)
        setContent {
            val themeSettings by remember { mutableStateOf(getInitialThemeSettings()) }
            InitializeApp(modifier = Modifier.fillMaxSize(), themeSettings = themeSettings)
        }

    }

    private fun getInitialThemeSettings(): ThemeSettings {
        return ThemeSettings(
            darkTheme = resources.configuration.isSystemInDarkTheme,
            androidTheme = true,
            disableDynamicTheming = false,
        )
    }

    private fun observeThemeSettings() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    isSystemInDarkTheme(),
                    viewModel.uiState
                ) { systemDark, uiState ->
                    ThemeSettings(
                        darkTheme = uiState.shouldUseDarkTheme(systemDark),
                        androidTheme = uiState.shouldUseAndroidTheme,
                        disableDynamicTheming = uiState.shouldDisableDynamicTheming,
                    )
                }
                    .collect { themeSettings ->
                        updateEdgeToEdgeDisplay(themeSettings.darkTheme)
                    }
            }
        }
    }

    private fun updateEdgeToEdgeDisplay(darkTheme: Boolean) {
        trace("appEdgeToEdge") {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    lightScrim = android.graphics.Color.TRANSPARENT,
                    darkScrim = android.graphics.Color.TRANSPARENT,
                ) { darkTheme },
                navigationBarStyle = SystemBarStyle.auto(
                    lightScrim = lightScrim.toArgb(),
                    darkScrim = darkScrim.toArgb(),
                ) { darkTheme },
            )
        }
    }

    private fun setupSplashScreen(splashScreen: SplashScreen) {
        splashScreen.setKeepOnScreenCondition {
            when (viewModel.uiState.value) {
                MainActivityUiState.Loading -> true
                is MainActivityUiState.Success -> false
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    startDestination: DestinationItem,
    onNavigateToJobDetail: (JobDetail) -> Unit,
    onNavigateToLogin: (String) -> Unit,
    onNavigateToVerifyEmail: () -> Unit,
    onNavigateToOnBoarding: () -> Unit,
    animatedVisibilityScope: AnimatedContentScope
) {
    val nestedNavigation = rememberAppState()
    var isShowBottomBar by remember { mutableStateOf(true) }
    Log.d("QQQ", "MainContent startDestination: ${startDestination.route}")
    val sharedTransitionScope = LocalSharedTransitionScope.current
    sharedTransitionScope?.apply {
        AppScaffold(
            backgroundColor = Color.Transparent,
            modifier = modifier
                .semantics {
                    testTagsAsResourceId = true
                },
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> AppSnackBar(snackbarData) }
                )
            },
            snackBarHostState = SnackbarHostState(),
            bottomBar = {
                if (isShowBottomBar) {
                    animatedVisibilityScope.apply {
                        BottomNavigationBar(
                            modifier = Modifier
                                .renderInSharedTransitionScopeOverlay(
                                    zIndexInOverlay = 1f,
                                )
                                .animateEnterExit(
                                    enter = fadeIn(nonSpatialExpressiveSpring()) + slideInVertically(
                                        spatialExpressiveSpring()
                                    ) {
                                        it
                                    },
                                    exit = fadeOut(nonSpatialExpressiveSpring()) + slideOutVertically(
                                        spatialExpressiveSpring()
                                    ) {
                                        it
                                    }
                                ),
                        ) {
                            InitBottomMainScreen(currentRoute = startDestination, onClick = {
                                nestedNavigation.navigateToBottomBarRoute(it.route)
                            }, changeItem = {
                                it.route == nestedNavigation.navController.currentBackStackEntryAsState().value?.destination?.route
                            })
                        }
                    }

                }
            },
            content = {
                Box(Modifier.fillMaxSize()) {
                    MainNavHost(
                        animatedVisibilityScope = animatedVisibilityScope,
                        appState = nestedNavigation,
                        startDestination = startDestination,
                        onNavigateToJobDetail = onNavigateToJobDetail,
                        onNavigateToLogin = onNavigateToLogin,
                        onNavigateToVerifyEmail = onNavigateToVerifyEmail,
                        onNavigateToOnBoarding = onNavigateToOnBoarding,
                        onShowBottomNav = {
                            isShowBottomBar = it
                        }
                    )
                }
            }
        )
    }

}

@Preview
@Composable
fun MainContentPreview() {
    AppPreviewWrapper {
        AnimatedVisibility(true) {
            /* MainContent(
                 it,
                 startDestination = Destinations.Main.Home,
                 onNavigateToJobDetail = { _ -> },
                 onNavigateToLogin = {},
                 onNavigateToVerifyEmail = { },
                 this,
             )*/
        }
    }
}


@Composable
fun ConnectionStatus() {
    val networkMonitor: NetworkMonitor = koinInject()
    val isOnline by networkMonitor.isOnline.collectAsState(initial = false)
    val connectionStatus = if (isOnline) "Online" else "Offline"
    Text(text = "Connection status: $connectionStatus", color = if (isOnline) Color.Green else Color.Red)
}
