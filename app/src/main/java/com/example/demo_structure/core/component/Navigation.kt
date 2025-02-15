package com.example.demo_structure.core.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.app.manager.theme.Generate
import com.example.demo_structure.core.navigation.DestinationItem
import com.example.demo_structure.core.navigation.Destinations

/**
 * Created by Phạm Sơn at 16:24/10/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */


@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    containerColor: Color = ProductXTheme.colorScheme.surface,
    contentColor: Color = ProductXTheme.colorScheme.onSurface,
    tonalElevation: Dp = 0.dp,
    windowInsets: WindowInsets = WindowInsets.navigationBars,
    content: @Composable() (RowScope.() -> Unit)
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
fun RowScope.InitBottomMainScreen(currentRoute: DestinationItem, onClick: (DestinationItem) -> Unit = {}, changeItem: @Composable (DestinationItem) -> Boolean) {
//    val currentRoute = appState.navController.currentBackStackEntryAsState().value?.destination?.route
    Destinations.Main.getEntries().forEach { item ->
        NavigationBarItem(
            modifier = Modifier.testTag(item.testTag),
            selected = currentRoute.route == item.route,
            onClick = {
                onClick.invoke(item)
            },
            label = {
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AppText(
                        text = item.title,
                        style = ProductXTheme.typography.Regular.Label.Small,
                        /* color = if (currentRoute.route == item.route)
                             ProductXTheme.colorScheme.tertiary
                         else
                             ProductXTheme.colorScheme.onTertiary,*/
                        color = if (changeItem(item)) ProductXTheme.colorScheme.tertiary
                        else ProductXTheme.colorScheme.onTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.testTag("Text_${item.title}")
                    )
                }

            },
            icon = {
                if (changeItem(item)) item.selectedIcon.Generate()
                else item.unselectedIcon.Generate()
            },
            alwaysShowLabel = true, // Consider showing labels only on selected items for better UX
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ProductXTheme.colorScheme.tertiary,
                selectedTextColor = ProductXTheme.colorScheme.tertiary,
                unselectedIconColor = ProductXTheme.colorScheme.onTertiary,
                unselectedTextColor = ProductXTheme.colorScheme.onTertiary,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationViewPreview() {
    BottomNavigationBar(
        modifier = Modifier,
        content = {
            InitBottomMainScreen(Destinations.Main.Home, onClick = { _ -> }, changeItem = { _ -> true })
        },
    )
}
