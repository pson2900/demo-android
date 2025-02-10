package com.example.demo_structure.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.demo_structure.app.InitializeApp
import com.example.demo_structure.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.manager.theme.LocalSharedTransitionScope
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.AppSurface
import com.example.demo_structure.core.component.BottomNavigationBar
import com.example.demo_structure.core.component.InitBottomMainScreen
import com.example.demo_structure.core.component.rememberScaffoldState
import com.example.demo_structure.core.navigation.MainNavHost
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.util.monitor.NetworkMonitor
import com.example.demo_structure.util.extension.isSystemInDarkTheme
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

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onNavigateToJobDetail: (Int, String) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToVerifyEmail: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val nestedNavigation = rememberAppState()
    val navBackStackEntry by nestedNavigation.navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No SharedElementScope found")

    AppScaffold(
        modifier = modifier
            .navigationBarsPadding()
            .statusBarsPadding()
            .semantics {
                testTagsAsResourceId = true
            },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.systemBars)
            .exclude(WindowInsets.ime),
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
        snackBarHostState = scaffoldState.snackBarHostState,
        bottomBar = {
            BottomNavigationBar(
                modifier = modifier,
            ) {
                InitBottomMainScreen(appState = nestedNavigation)
            }
        },
        content = { padding ->
            AppSurface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = padding)
            ) {
                MainNavHost(
                    appState = nestedNavigation,
                    onNavigateToJobDetail = onNavigateToJobDetail,
                    onNavigateToLogin = onNavigateToLogin,
                    onNavigateToVerifyEmail = onNavigateToVerifyEmail
                )
            }
        }
    )
}

@Preview
@Composable
fun MainContentPreview() {
    AppPreviewWrapper {
        MainContent(
            it,
            onNavigateToJobDetail = { _, _ -> },
            onNavigateToLogin = {},
            onNavigateToVerifyEmail = { },
        )
    }
}


@Composable
fun ConnectionStatus() {
    val networkMonitor: NetworkMonitor = koinInject()
    val isOnline by networkMonitor.isOnline.collectAsState(initial = false)
    val connectionStatus = if (isOnline) "Online" else "Offline"
    Text(text = "Connection status: $connectionStatus", color = if (isOnline) Color.Green else Color.Red)
}
