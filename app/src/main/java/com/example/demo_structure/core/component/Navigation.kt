package com.example.demo_structure.core.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.demo_structure.core.navigation.AppState
import com.example.demo_structure.core.navigation.rememberAppState
import com.example.demo_structure.screen.main.MainDestination
import com.example.demo_structure.theme.ProductXTheme
import com.example.demo_structure.theme.toIcon
import com.example.demo_structure.util.AlwaysOnlineNetworkMonitor

/**
 * Created by Phạm Sơn at 16:24/10/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */


@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    appState: AppState,
    containerColor: Color = ProductXTheme.colors.background,
    contentColor: Color = ProductXTheme.colors.secondary,
    tonalElevation: Dp = 0.dp,
    windowInsets: WindowInsets = WindowInsets.navigationBars
) {
    val currentRoute = appState.navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        modifier = modifier,
        tonalElevation = tonalElevation,
        windowInsets = windowInsets,
        containerColor = containerColor, // Use the surface color from theme
        contentColor = contentColor // Text and icon color from theme
    ) {
        MainDestination.entries.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    appState.navigateToBottomBarRoute(item.route)
                },
                label = {
                    Text(
                        text = item.name,
//                        style = ProductXTheme.typography.body2.copy(fontSize = 10.sp)
                    )
                },
                icon = {
                    if (currentRoute == item.route) {
                        item.selectedIcon.toIcon()
                    } else {
                        item.unselectedIcon.toIcon()
                    }
                },
                alwaysShowLabel = false, // Consider showing labels only on selected items for better UX
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ProductXTheme.colors.iconSecondary,
                    selectedTextColor = ProductXTheme.colors.iconSecondary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationViewPreview() {
    ProductXPreviewWrapper {
        var appState = rememberAppState(networkMonitor = AlwaysOnlineNetworkMonitor())
        BottomNavigationBar(
            modifier = Modifier,
            appState = appState
        )
    }
}
