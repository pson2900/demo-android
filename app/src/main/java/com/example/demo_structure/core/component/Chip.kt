package com.example.demo_structure.core.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.demo_structure.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 09:30/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun ProductXFilterChip(
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable () -> Unit,
) {
    FilterChip(
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = {
            ProvideTextStyle(value = ProductXTheme.typography.RegularLabelLarge) {
                label()
            }
        },
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
        border = FilterChipDefaults.filterChipBorder(
            enabled = enabled,
            selected = selected,
            borderColor = ProductXTheme.colorScheme.background,
            selectedBorderColor = ProductXTheme.colorScheme.background,
            disabledBorderColor = ProductXTheme.colorScheme.background.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            disabledSelectedBorderColor = ProductXTheme.colorScheme.background.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            selectedBorderWidth = ChipDefaults.ChipBorderWidth,
        ),
        colors = FilterChipDefaults.filterChipColors(
            labelColor = ProductXTheme.colorScheme.background,
            iconColor = ProductXTheme.colorScheme.background,
            disabledContainerColor = if (selected) {
                ProductXTheme.colorScheme.background.copy(
                    alpha = ChipDefaults.DISABLED_CHIP_CONTAINER_ALPHA,
                )
            } else {
                Color.Transparent
            },
            disabledLabelColor = ProductXTheme.colorScheme.background.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            disabledLeadingIconColor = ProductXTheme.colorScheme.background.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            selectedContainerColor = ProductXTheme.colorScheme.primary,
            selectedLabelColor = ProductXTheme.colorScheme.background,
            selectedLeadingIconColor = ProductXTheme.colorScheme.background,
        ),
    )
}

@ThemePreviews
@Composable
fun ChipPreview() {
    AppPreviewWrapper {
        ProductXFilterChip(selected = true, onSelectedChange = {}) {
            Text("Chip")
        }
    }
}

/**
 * Now in Android chip default values.
 */
object ChipDefaults {
    // TODO: File bug
    // FilterChip default values aren't exposed via FilterChipDefaults
    const val DISABLED_CHIP_CONTAINER_ALPHA = 0.12f
    const val DISABLED_CHIP_CONTENT_ALPHA = 0.38f
    val ChipBorderWidth = 1.dp
}
