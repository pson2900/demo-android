package com.example.demo_structure.core.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.app.manager.theme.toIcon
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.screen.main.MainDestination
import com.example.demo_structure.util.AlwaysOnlineNetworkMonitor

/**
 * Created by Phạm Sơn at 16:24/10/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */


@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    containerColor: Color = ProductXTheme.colors.background,
    contentColor: Color = ProductXTheme.colors.secondary,
    tonalElevation: Dp = 0.dp,
    windowInsets: WindowInsets = WindowInsets.navigationBars,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = tonalElevation,
        windowInsets = windowInsets,
        containerColor = containerColor,
        contentColor = contentColor,
        content = content
    )
}

@Composable
fun RowScope.initBottomMainScreen(appState: AppState) {
    val currentRoute = appState.navController.currentBackStackEntryAsState().value?.destination?.route
    Destinations.Main.getEntries().forEach { item ->
        NavigationBarItem(
            modifier = Modifier.testTag(item.testTag),
            selected = currentRoute == item.route,
            onClick = {
                appState.navigateToBottomBarRoute(item.route)
            },
            label = {
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (currentRoute == item.route) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.testTag("Text_${item.title}")
                    )
                }

            },
            icon = {
                if (currentRoute == item.route) item.selectedIcon.toIcon()
                else item.unselectedIcon.toIcon()
            },
            alwaysShowLabel = true, // Consider showing labels only on selected items for better UX
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ProductXTheme.colors.tertiary,
                selectedTextColor = ProductXTheme.colors.tertiary,
                unselectedIconColor = ProductXTheme.colors.onTertiary,
                unselectedTextColor = ProductXTheme.colors.onTertiary,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationViewPreview() {
    AppPreviewWrapper {
        var appState = rememberAppState(networkMonitor = AlwaysOnlineNetworkMonitor())
        BottomNavigationBar(
            modifier = Modifier,
        ) {
            initBottomMainScreen(appState)
        }
    }
}
