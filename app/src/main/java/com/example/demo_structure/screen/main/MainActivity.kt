package com.example.demo_structure.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.trace
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.demo_structure.app.InitializeApp
import com.example.demo_structure.app.LocalNavAnimatedVisibilityScope
import com.example.demo_structure.app.LocalSharedTransitionScope
import com.example.demo_structure.app.manager.LanguageManager
import com.example.demo_structure.core.component.AppBackground
import com.example.demo_structure.core.component.AppGradientBackground
import com.example.demo_structure.core.component.BottomNavigationBar
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.core.component.ProductXScaffold
import com.example.demo_structure.core.component.ProductXSnackBar
import com.example.demo_structure.core.component.rememberScaffoldState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.screen.home.toHomeScreen
import com.example.demo_structure.screen.search_result.toSearchResultScreen
import com.example.demo_structure.screen.user.toUserScreen
import com.example.demo_structure.theme.LocalGradientColors
import com.example.demo_structure.util.NetworkMonitor
import com.example.demo_structure.util.isSystemInDarkTheme
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

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
        var themeSettings by mutableStateOf(
            ThemeSettings(
                darkTheme = resources.configuration.isSystemInDarkTheme,
                androidTheme = true,
                disableDynamicTheming = false,
            ),
        )
        // Update the uiState
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    isSystemInDarkTheme(),
                    viewModel.uiState
                )
                { systemDark, uiState ->
                    ThemeSettings(
                        darkTheme = uiState.shouldUseDarkTheme(systemDark),
                        androidTheme = uiState.shouldUseAndroidTheme,
                        disableDynamicTheming = uiState.shouldDisableDynamicTheming,
                    )
                }
                    .onEach { themeSettings = it }
                    .map { it.darkTheme }
                    .distinctUntilChanged()
                    .collect { darkTheme ->
                        trace("appEdgeToEdge") {
                            // Turn off the decor fitting system windows, which allows us to handle insets,
                            // including IME animations, and go edge-to-edge.
                            // This is the same parameters as the default enableEdgeToEdge call, but we manually
                            // resolve whether or not to show dark theme using uiState, since it can be different
                            // than the configuration's dark theme value based on the user preference.
                            enableEdgeToEdge(
                                statusBarStyle = SystemBarStyle.auto(
                                    lightScrim = android.graphics.Color.TRANSPARENT,
                                    darkScrim = android.graphics.Color.TRANSPARENT,
                                ) { darkTheme },
                                navigationBarStyle = SystemBarStyle.auto(
                                    lightScrim = lightScrim,
                                    darkScrim = darkScrim,
                                ) { darkTheme },
                            )
                        }
                    }
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (viewModel.uiState.value) {
                MainActivityUiState.Loading -> true
                is MainActivityUiState.Success -> false
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppBackground(modifier = Modifier) {
                AppGradientBackground(gradientColors = LocalGradientColors.current) {
                    val snackbarHostState = remember { SnackbarHostState() }
                    val appState = rememberAppState(networkMonitor = koinInject())
                    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

                    // If user is not connected to the internet show a snack bar to inform them.
                    val notConnectedMessage = "notConnectedMessage"
                    LaunchedEffect(isOffline) {
                        if (isOffline) {
                            snackbarHostState.showSnackbar(
                                message = notConnectedMessage,
                                duration = Indefinite,
                            )
                        }
                    }
                    InitializeApp(appState = appState, themeSettings = themeSettings)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    onNavigateToJobDetail: (Int, String) -> Unit,
    onNavigateToLogin: () -> Unit
) {

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onNavigateToJobDetail: (Int, String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val nestedNavigation = rememberAppState(networkMonitor = koinInject())
    val navBackStackEntry by nestedNavigation.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No SharedElementScope found")

    ProductXScaffold(
        modifier = modifier.semantics {
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
                snackbar = { snackbarData -> ProductXSnackBar(snackbarData) }
            )
        },
        snackBarHostState = scaffoldState.snackBarHostState,
        bottomBar = {
            BottomNavigationBar(modifier = modifier, appState = nestedNavigation)
        },
        content = { padding ->
            NavHost(
                navController = nestedNavigation.navController,
                startDestination = Destinations.HOME_ROUTE,
                modifier = Modifier.padding(padding)
            ) {
                /*  mainNavGraph(
                      nestedAppState = nestedNavigation,
                      onNavigateToJobDetail = onNavigateToJobDetail,
                      onNavigateToLogin = onNavigateToLogin,
                      modifier = modifier
                          .fillMaxSize()
                          .padding(
                              top = padding.calculateTopPadding(), start = 0.dp, end = 0.dp,
                              bottom = 0.dp
                          )
                          .consumeWindowInsets(padding)
                  )*/

                toHomeScreen(
                    nestedNavigation,
                    onNavigateToJobDetail = onNavigateToJobDetail,
                )
                toSearchResultScreen(
                    nestedNavigation,
                    modifier = modifier,
                    onTopicClick = {

                    },
                )
                toUserScreen(
                    nestedNavigation,
                    modifier = modifier,
                    onNavigateToLogin = onNavigateToLogin,
                )

            }
        }

    )
}

@Preview
@Composable
fun MainContentPreview() {
    ProductXPreviewWrapper {
        val context = LocalContext.current
        val appState = rememberAppState(networkMonitor = koinInject())
        val snackbarHostState = SnackbarHostState()
        ProductXScaffold(
            modifier = Modifier,

            snackBarHostState = SnackbarHostState(),
            bottomBar = {
                BottomNavigationBar(Modifier, appState)
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Row {
                    ConnectionStatus()
                    Button(onClick = {
                        LanguageManager.setLocale(context, "en")
                    }) {
                        Text("Change EN")

                    }
                    Button(onClick = {
                        LanguageManager.setLocale(context, "vi")
                    }) {
                        Text("Change VI")
                    }
                }
            }
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
