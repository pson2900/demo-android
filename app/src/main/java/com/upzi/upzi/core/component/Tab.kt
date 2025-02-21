package com.upzi.upzi.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 13:32/20/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = {
            val style = ProductXTheme.typography.SemiBold.Label.Large.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(
                value = style,
                content = {
                    Box(modifier = Modifier.padding(top = TabDefaults.TabTopPadding)) {
                        text()
                    }
                },
            )
        },
    )
}


@Composable
fun AppTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = ProductXTheme.colorScheme.surface,
        contentColor = ProductXTheme.colorScheme.onSurface,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = 2.dp,
                color = ProductXTheme.colorScheme.onSurface,
            )
        },
        tabs = tabs,
    )
}

@ThemePreviews
@Composable
fun TabsPreview() {
    AppPreviewWrapper {
        val titles = listOf("MyJob", "Applied", "Saved")
        AppTabRow(selectedTabIndex = 0) {
            titles.forEachIndexed { index, title ->
                AppTab(
                    selected = index == 0,
                    onClick = { },
                    text = { Text(text = title) },
                )
            }
        }
    }
}

object TabDefaults {
    val TabTopPadding = 7.dp
}
