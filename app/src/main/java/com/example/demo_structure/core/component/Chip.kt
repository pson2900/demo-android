package com.example.demo_structure.core.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_structure.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 09:30/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppChip(
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
            ProvideTextStyle(value = ProductXTheme.typography.Regular.Label.Large) {
                label()
            }
        },
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
        border = FilterChipDefaults.filterChipBorder(
            enabled = enabled,
            selected = selected,
            borderColor = ProductXTheme.colorScheme.outline,
            selectedBorderColor = ProductXTheme.colorScheme.outline,
            disabledBorderColor = ProductXTheme.colorScheme.surface.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            disabledSelectedBorderColor = ProductXTheme.colorScheme.surface.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            selectedBorderWidth = ChipDefaults.ChipBorderWidth,
        ),

        colors = FilterChipDefaults.filterChipColors(
            labelColor = ProductXTheme.colorScheme.onPrimary,
            iconColor = ProductXTheme.colorScheme.onPrimary,
            disabledContainerColor = if (selected) {
                ProductXTheme.colorScheme.surface.copy(
                    alpha = ChipDefaults.DISABLED_CHIP_CONTAINER_ALPHA,
                )
            } else {
                Color.Transparent
            },
            disabledLabelColor = ProductXTheme.colorScheme.surface.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            disabledLeadingIconColor = ProductXTheme.colorScheme.surface.copy(
                alpha = ChipDefaults.DISABLED_CHIP_CONTENT_ALPHA,
            ),
            selectedContainerColor = ProductXTheme.colorScheme.surface,
            selectedLabelColor = ProductXTheme.colorScheme.primary,
            selectedLeadingIconColor = ProductXTheme.colorScheme.primary,
        ),
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun ChipPreview() {
    AppChip(selected = true, onSelectedChange = {}) {
        Text("Chip")
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
