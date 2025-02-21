package com.upzi.upzi.core.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.GenerateIcon
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.app.manager.theme.hexToColor
import com.upzi.domain.ifNotNull

/**
 * Created by Phạm Sơn at 09:30/16/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun AppFilterChip(
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
fun AppSuggestionChip(
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable () -> Unit,
) {
    SuggestionChip(onClick = { },
        label = { Text("Suggestion Chip") },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            iconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            enabled = false,
            borderColor = MaterialTheme.colorScheme.outline,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            borderWidth = 1.dp,
        ),
        icon = {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Idea",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        })
}


@Composable
fun AppInputChip(
    modifier: Modifier = Modifier,
    isSelect: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    label: String = "Input Chip",
    iconRight: Int? = null,
    iconLeft: Int? = null,
) {
    val (isSelectState, setSelectState) = remember { mutableStateOf(isSelect) }
    AppBox(
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.dp, color =  if (isSelectState) Color.Transparent else hexToColor("#E2E8F0")),
        backgroundColor = if (isSelectState) hexToColor("#EBE7FD") else hexToColor("#FFFFFF"),
        onClick = {
            setSelectState(!isSelectState)
            onSelectedChange.invoke(!isSelectState)
        }
    ) {
        Row(modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            iconRight.ifNotNull {
                it.GenerateIcon(
                    color = if (isSelectState) hexToColor("#6142F3") else hexToColor("#0F172A")
                )
            }
            Text(
                label,
                color = if (isSelectState) hexToColor("#6142F3") else hexToColor("#0F172A")
            )
            iconLeft.ifNotNull {
                it.GenerateIcon(
                    color = if (isSelectState) hexToColor("#6142F3") else hexToColor("#0F172A")
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun ChipPreview() {
    Column {
        AppFilterChip(selected = true, onSelectedChange = {}) {
            Text("AppFilterChip")
        }
        AppSuggestionChip(selected = true, onSelectedChange = {}) {
            Text("AppSuggestionChip")
        }
        AppInputChip(isSelect = true, onSelectedChange = {}, label = "AppSuggestionChip")
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
