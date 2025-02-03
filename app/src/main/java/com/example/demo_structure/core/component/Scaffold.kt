package com.example.demo_structure.core.component

import android.content.res.Resources
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
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
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.app.manager.theme.ProductXTheme
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
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    backgroundColor: Color = ProductXTheme.backgroundTheme.color,
    contentColor: Color = ProductXTheme.colors.secondary,
    contentWindowInsets: WindowInsets = WindowInsets.safeContent,
    content: @Composable (PaddingValues) -> Unit
) {
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
        contentWindowInsets = contentWindowInsets,
        content = content
    )
}

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


@OptIn(ExperimentalSharedTransitionApi::class)

@Composable
fun AppPreviewWrapper(content: @Composable (Modifier) -> Unit) {
    ApplicationTheme {
        val modifier: Modifier = Modifier
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                CompositionLocalProvider(
                    LocalSharedTransitionScope provides this@SharedTransitionLayout,
                    LocalNavAnimatedVisibilityScope provides this
                ) {
                    AppSurface(
                        modifier = modifier,
                        shape = RectangleShape,
                        color = ProductXTheme.colors.background,
                        contentColor = ProductXTheme.colors.secondary,
                        elevation = 0.dp,
                        content = {
                            content(modifier.fillMaxSize())
                        }
                    )

                }
            }
        }
    }
}

