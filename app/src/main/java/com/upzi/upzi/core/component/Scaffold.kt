package com.upzi.upzi.core.component

import android.content.res.Resources
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.ApplicationTheme
import com.upzi.upzi.app.manager.theme.LocalNavAnimatedVisibilityScope
import com.upzi.upzi.app.manager.theme.LocalSharedTransitionScope
import com.upzi.upzi.app.manager.theme.ProductXTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Phạm Sơn at 13:04/10/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

@Composable
fun rememberScaffoldState(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): ScaffoldState = remember(snackBarHostState, snackBarManager, resources, coroutineScope) {
    ScaffoldState(snackBarHostState, snackBarManager, resources, coroutineScope)
}

class ScaffoldState(
    val snackBarHostState: SnackbarHostState,
    private val snackBarManager: SnackBarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    // Process snackbars coming from SnackbarManager
    init {
        coroutineScope.launch {
            SnackBarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getText(message.messageId)
                    // Notify the SnackbarManager so it can remove the current message from the list
                    SnackBarManager.setMessageShown(message.id)
                    // Display the snackbar on the screen. `showSnackbar` is a function
                    // that suspends until the snackbar disappears from the screen
                    snackBarHostState.showSnackbar(text.toString())
                }
            }
        }
    }
}

/**
 * Wrap Material [androidx.compose.material3.Scaffold] and set [ProductXTheme] colors.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    backgroundColor: Color,
//    contentWindowInsets: WindowInsets = WindowInsets.safeDrawing,
    contentColor: Color = ProductXTheme.colorScheme.onSurface,
    content: @Composable (PaddingValues) -> Unit
) {
    ApplicationTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalNavAnimatedVisibilityScope provides this,
                    content = {
                        Scaffold(
                            modifier = modifier,
                            topBar = topBar,
                            bottomBar = bottomBar,
                            snackbarHost = {
                                snackbarHost(snackBarHostState)
                            },
                            floatingActionButton = floatingActionButton,
                            floatingActionButtonPosition = floatingActionButtonPosition,
                            containerColor = backgroundColor,
                            contentColor = contentColor,
                            contentWindowInsets = WindowInsets.safeDrawing,
                          /*  contentWindowInsets = ScaffoldDefaults
                                .contentWindowInsets
                                .exclude(WindowInsets.systemBars)
                                .exclude(WindowInsets.ime)
                                .exclude(WindowInsets.navigationBars)
                                .exclude(WindowInsets.statusBars)
                                .exclude(WindowInsets.safeDrawing),*/
                            /*content = {
                                content(
                                    Modifier
                                        .padding(it)
                                        .background(backgroundColor)
                                )
                            }*/
                            content = content
                        )
                        /*{
                            Box(modifier = Modifier.padding(it)) {

                            }
                        }*/
//                        )

                    }
                )
            }
        }
    }

}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}


@Composable
fun AppPreviewWrapper(content: @Composable (Modifier) -> Unit) {
    ApplicationTheme {
        AppSurface(
            modifier = Modifier,
            shape = RectangleShape,
            backgroundColor = ProductXTheme.colorScheme.background_1,
            contentColor = ProductXTheme.colorScheme.onSurface,
            elevation = 0.dp
        ) {
            content(Modifier)
        }
    }

}

