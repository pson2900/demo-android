/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo_structure.app.manager.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

fun hexToColor(hex: String): Color {
    // 1. Remove the '#' if it exists
    val cleanHex = hex.removePrefix("#")

    // 2. Handle different hex lengths (3, 4, 6, or 8 characters)
    return when (cleanHex.length) {
        3 -> {
            // Convert #RGB to #RRGGBB (Example: #abc to #aabbcc)
            val r = cleanHex[0].toString().repeat(2).toInt(16)
            val g = cleanHex[1].toString().repeat(2).toInt(16)
            val b = cleanHex[2].toString().repeat(2).toInt(16)
            Color(0xFF000000 + (r shl 16) + (g shl 8) + b)
        }

        4 -> {
            // Convert #RGBA to #RRGGBBAA (Example: #abcd to #aabbccdd)
            val r = cleanHex[0].toString().repeat(2).toInt(16)
            val g = cleanHex[1].toString().repeat(2).toInt(16)
            val b = cleanHex[2].toString().repeat(2).toInt(16)
            val a = cleanHex[3].toString().repeat(2).toInt(16)
            Color((a shl 24) + (r shl 16) + (g shl 8) + b)
        }

        6 -> {
            // Convert #RRGGBB to Int
            val colorInt = cleanHex.toInt(16)
            Color(0xFF000000 + colorInt) // Add full opacity

        }

        8 -> {
            // Convert #RRGGBBAA to Int
            val colorInt = cleanHex.toLong(16)
            Color(colorInt)
        }

        else -> Color.Transparent // Return transparent if the hex code is invalid
    }
}

fun ColorScheme.toColorTheme(
    background_1 : Color,
    background_2: Color
): ColorTheme {
    return ColorTheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,

        inversePrimary = inversePrimary,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,

        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = secondaryContainer,

        tertiaryContainer = tertiaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        onTertiaryContainer = onTertiaryContainer,

        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,

        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surfaceTint,
        surfaceContainer = surfaceContainer,
        surfaceContainerHigh = surfaceContainerHigh,
        surfaceContainerHighest = surfaceContainerHighest,
        surfaceContainerLow = surfaceContainerLow,
        surfaceContainerLowest = surfaceContainerLowest,

        outline = outline,
        outlineVariant = outlineVariant,

        background_1 = background_1,
        background_2 = background_2,

    )
}



val defaultColorScheme = ColorScheme(
    // Primary Colors (Brand)
    primary = hexToColor("#775AFF"),
    onPrimary = Color.White,  // Contrasting color for text/icons on primary
    primaryContainer = hexToColor("#D0BCFF"), // Lighter shade of primary
    onPrimaryContainer = hexToColor("#1C1B1F"), // Contrasting color on container
    inversePrimary = hexToColor("#D0BCFF"),     // For elements that need to stand out on dark surfaces

    // Secondary Colors (Accent)
    secondary = hexToColor("#F1F5F9"),
    onSecondary = hexToColor("#1C1B1F"),
    secondaryContainer = hexToColor("#FFFFFF"),
    onSecondaryContainer = hexToColor("#1C1B1F"),

    // Tertiary Colors (Subtle Accent or Highlight)
    tertiary = hexToColor("#775AFF"), // Consider using a different accent color
    onTertiary = Color.White,
    tertiaryContainer = hexToColor("#775AFF"),
    onTertiaryContainer = hexToColor("#1C1B1F"),

    // Error Colors (For Validation or Warnings)
    error = hexToColor("#EC221F"),
    onError = Color.White,
    errorContainer = hexToColor("#F2B8B5"),
    onErrorContainer = hexToColor("#601410"),

    // Neutral Colors (Background & Surfaces)
    background = hexToColor("#F1F5F9"),
    onBackground = hexToColor("#1C1B1F"),
    surface = hexToColor("#FFFFFF"),
    onSurface = hexToColor("#1C1B1F"),
    surfaceVariant = hexToColor("#E7E0EC"),  // Subpart
    onSurfaceVariant = hexToColor("#49454F"), //text above subpart
    inverseSurface = hexToColor("#313033"),  //Color inverted text
    inverseOnSurface = hexToColor("#E6E1E5"), //Contrasting inverted

    //Surface Tonal Palettes (Elevation & Depth - Use with caution)
    surfaceContainer = hexToColor("#FFFFFF"),
    surfaceContainerLow = hexToColor("#FFFFFF"),
    surfaceContainerLowest = hexToColor("#FFFFFF"),
    surfaceContainerHigh = hexToColor("#FFFFFF"),
    surfaceContainerHighest = hexToColor("#FFFFFF"),
    surfaceBright = hexToColor("#FFFFFF"),
    surfaceDim = hexToColor("#FFFFFF"),

    //Other key additions, Key for component
    surfaceTint = hexToColor("#775AFF").copy(alpha = 0.1f), // Lighter color on components
    outline = hexToColor("#79747E"), // Dividers
    outlineVariant = hexToColor("#CAC4D0"),//Lighter divider
    scrim = Color.Black.copy(alpha = 0.3f)  // scrim
)

@Immutable
data class ColorTheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,

    val inverseSurface: Color,
    val inverseOnSurface: Color,
    val inversePrimary: Color,

    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,

    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,

    val background_1 : Color,
    val background_2: Color,

    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,

    val outline: Color,
    val outlineVariant: Color,

    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val surfaceTint: Color = primary,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val surfaceContainerLow: Color,
    val surfaceContainerLowest: Color,
)

