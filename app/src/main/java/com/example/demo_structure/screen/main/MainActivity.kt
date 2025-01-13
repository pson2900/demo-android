package com.example.demo_structure.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
import com.example.demo_structure.core.component.AppBackground
import com.example.demo_structure.core.component.AppGradientBackground
import com.example.demo_structure.core.component.BottomNavigationBar
import com.example.demo_structure.core.component.ProductXScaffold
import com.example.demo_structure.core.component.ProductXSnackBar
import com.example.demo_structure.core.component.rememberScaffoldState
import com.example.demo_structure.theme.LocalGradientColors
import com.example.demo_structure.theme.ProductXApplicationTheme
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.util.AlwaysOnlineNetworkMonitor
import com.example.demo_structure.util.isSystemInDarkTheme
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
    val viewModel: MainViewModel by lazy { MainViewModel() }
    val networkMonitor by lazy {
        AlwaysOnlineNetworkMonitor()
    }

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
//                viewModel.uiState
//                    .onEach { viewModel.uiState.value = it }
//                    .collect {
//
//                    }
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
                AppGradientBackground(
                    gradientColors = LocalGradientColors.current,
                ) {
                    val snackbarHostState = remember { SnackbarHostState() }
                    val appState = rememberAppState(networkMonitor = networkMonitor)
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
           /*         binding.changeLanguageVi.setOnClickListener {
                        languageManager.setLocale(this, "vi")
                        recreate()
                        Log.d("QQQ", "đổi sang Tiếng Việt")
                    }

                    binding.changeLanguageEn.setOnClickListener {
                        languageManager.setLocale(this, "en")
                        recreate()
                        Log.d("QQQ", "đổi sang Tiếng Anh")
                    }*/
                    InitializeApp(appState = appState, themeSettings = themeSettings)
                }
            }
        }
    }
}


val networkMonitor by lazy {
    AlwaysOnlineNetworkMonitor()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onNavigateToJobDetail: (Int, String) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val appState = rememberAppState(networkMonitor = networkMonitor)
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
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
            BottomNavigationBar(modifier = modifier, appState = appState)
        },
        content = { padding ->
            NavHost(
                navController = appState.navController,
                startDestination = MainDestination.HOME.route
            ) {
                addHomeGraph(
                    onSnackSelected = onNavigateToJobDetail,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                )
            }
        }

    )
}

@Preview
@Composable
fun MainContentPreview() {
    ProductXApplicationTheme {
        val appState = rememberAppState(networkMonitor = networkMonitor)
        val snackbarHostState = SnackbarHostState()
        ProductXScaffold(
            modifier = Modifier,

            snackBarHostState = SnackbarHostState(),
            bottomBar = {
                BottomNavigationBar(Modifier, appState)
//
//                MainContent(modifier = Modifier,
//                    appState = appState,
//                    onNavigateToJobDetail = { jobId, origin, from ->
//                        appState.navigateToJobDetail(jobId, origin, from)
//                    })
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding))
        }
    }
}
