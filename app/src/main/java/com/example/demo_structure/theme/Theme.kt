package com.example.demo_structure.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Light default theme color scheme
 */
@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Purple40, // The primary color of the app, e.g., for main buttons
    onPrimary = Color.Black, // Color for text/icons on the primary color
    primaryContainer = Purple90, // Background color for containers based on primary
    onPrimaryContainer = Purple10, // Color for text/icons on the primaryContainer
    secondary = Orange40, // Secondary color, used for less important parts
    onSecondary = Color.Black, // Color for text/icons on the secondary color
    secondaryContainer = Orange90, // Background color for containers based on secondary
    onSecondaryContainer = Orange10, // Color for text/icons on the secondaryContainer
    tertiary = Blue40, // Tertiary color, used for accents or small details
    onTertiary = Color.Blue, // Color for text/icons on the tertiary color
    tertiaryContainer = Blue90, // Background color for containers based on tertiary
    onTertiaryContainer = Blue10, // Color for text/icons on the tertiaryContainer
    error = Red40, // Color for errors, e.g., error messages
    onError = Color.Red, // Color for text/icons on the error color
    errorContainer = Red90, // Background color for containers containing errors
    onErrorContainer = Red10, // Color for text/icons on the errorContainer
    background = DarkPurpleGray99, // Background color of the entire app
    onBackground = DarkPurpleGray10, // Color for text/icons on the background
    surface = DarkPurpleGray99, // Color for surfaces like cards or bottom sheets
    onSurface = DarkPurpleGray10, // Color for text/icons on the surface
    surfaceVariant = PurpleGray90, // Variation of the surface color, for subparts
    onSurfaceVariant = PurpleGray30, // Color for text/icons on the surfaceVariant
    inverseSurface = DarkPurpleGray20, // Color for inverted elements (light text on dark backgrounds)
    inverseOnSurface = DarkPurpleGray95, // Color for text/icons on the inverseSurface
    outline = PurpleGray50, // Color for borders, dividers or outlines
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = Purple80, // The primary color of the app (dark theme version)
    onPrimary = Purple20, // Color for text/icons on the primary color (dark theme)
    primaryContainer = Purple30, // Background color for containers based on primary (dark theme)
    onPrimaryContainer = Purple90, // Color for text/icons on the primaryContainer (dark theme)
    secondary = Orange80, // Secondary color (dark theme)
    onSecondary = Orange20, // Color for text/icons on the secondary color (dark theme)
    secondaryContainer = Orange30, // Background color for containers based on secondary (dark theme)
    onSecondaryContainer = Orange90, // Color for text/icons on the secondaryContainer (dark theme)
    tertiary = Blue80, // Tertiary color (dark theme)
    onTertiary = Blue20, // Color for text/icons on the tertiary color (dark theme)
    tertiaryContainer = Blue30, // Background color for containers based on tertiary (dark theme)
    onTertiaryContainer = Blue90, // Color for text/icons on the tertiaryContainer (dark theme)
    error = Red80, // Color for errors (dark theme)
    onError = Red20, // Color for text/icons on the error color (dark theme)
    errorContainer = Red30, // Background color for containers containing errors (dark theme)
    onErrorContainer = Red90, // Color for text/icons on the errorContainer (dark theme)
    background = DarkPurpleGray10, // Background color of the entire app (dark theme)
    onBackground = DarkPurpleGray90, // Color for text/icons on the background (dark theme)
    surface = DarkPurpleGray10, // Color for surfaces (dark theme)
    onSurface = DarkPurpleGray90, // Color for text/icons on the surface (dark theme)
    surfaceVariant = PurpleGray30, // Variation of the surface color (dark theme)
    onSurfaceVariant = PurpleGray80, // Color for text/icons on the surfaceVariant (dark theme)
    inverseSurface = DarkPurpleGray90, // Color for inverted elements (light text on dark backgrounds) (dark theme)
    inverseOnSurface = DarkPurpleGray10, // Color for text/icons on the inverseSurface (dark theme)
    outline = PurpleGray60, // Color for borders, dividers or outlines (dark theme)
)

/**
 * Light Android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = Green40, // The primary color of the app (Android theme)
    onPrimary = Color.Black, // Color for text/icons on the primary color (Android theme)
    primaryContainer = Green90, // Background color for containers based on primary (Android theme)
    onPrimaryContainer = Green10, // Color for text/icons on the primaryContainer (Android theme)
    secondary = DarkGreen40, // Secondary color (Android theme)
    onSecondary = Color.Black, // Color for text/icons on the secondary color (Android theme)
    secondaryContainer = DarkGreen90, // Background color for containers based on secondary (Android theme)
    onSecondaryContainer = DarkGreen10, // Color for text/icons on the secondaryContainer (Android theme)
    tertiary = Teal40, // Tertiary color (Android theme)
    onTertiary = Color.Blue, // Color for text/icons on the tertiary color (Android theme)
    tertiaryContainer = Teal90, // Background color for containers based on tertiary (Android theme)
    onTertiaryContainer = Teal10, // Color for text/icons on the tertiaryContainer (Android theme)
    error = Red40, // Color for errors (Android theme)
    onError = Color.Red, // Color for text/icons on the error color (Android theme)
    errorContainer = Red90, // Background color for containers containing errors (Android theme)
    onErrorContainer = Red10, // Color for text/icons on the errorContainer (Android theme)
    background = DarkGreenGray99, // Background color of the entire app (Android theme)
    onBackground = DarkGreenGray10, // Color for text/icons on the background (Android theme)
    surface = DarkGreenGray99, // Color for surfaces (Android theme)
    onSurface = DarkGreenGray10, // Color for text/icons on the surface (Android theme)
    surfaceVariant = GreenGray90, // Variation of the surface color (Android theme)
    onSurfaceVariant = GreenGray30, // Color for text/icons on the surfaceVariant (Android theme)
    inverseSurface = DarkGreenGray20, // Color for inverted elements (light text on dark backgrounds) (Android theme)
    inverseOnSurface = DarkGreenGray95, // Color for text/icons on the inverseSurface (Android theme)
    outline = GreenGray50,  // Color for borders, dividers or outlines (Android theme)
)

/**
 * Dark Android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = Green80, // The primary color of the app (dark Android theme)
    onPrimary = Green20, // Color for text/icons on the primary color (dark Android theme)
    primaryContainer = Green30, // Background color for containers based on primary (dark Android theme)
    onPrimaryContainer = Green90, // Color for text/icons on the primaryContainer (dark Android theme)
    secondary = DarkGreen80, // Secondary color (dark Android theme)
    onSecondary = DarkGreen20, // Color for text/icons on the secondary color (dark Android theme)
    secondaryContainer = DarkGreen30, // Background color for containers based on secondary (dark Android theme)
    onSecondaryContainer = DarkGreen90, // Color for text/icons on the secondaryContainer (dark Android theme)
    tertiary = Teal80, // Tertiary color (dark Android theme)
    onTertiary = Teal20, // Color for text/icons on the tertiary color (dark Android theme)
    tertiaryContainer = Teal30, // Background color for containers based on tertiary (dark Android theme)
    onTertiaryContainer = Teal90, // Color for text/icons on the tertiaryContainer (dark Android theme)
    error = Red80, // Color for errors (dark Android theme)
    onError = Red20, // Color for text/icons on the error color (dark Android theme)
    errorContainer = Red30, // Background color for containers containing errors (dark Android theme)
    onErrorContainer = Red90, // Color for text/icons on the errorContainer (dark Android theme)
    background = DarkGreenGray10, // Background color of the entire app (dark Android theme)
    onBackground = DarkGreenGray90, // Color for text/icons on the background (dark Android theme)
    surface = DarkGreenGray10, // Color for surfaces (dark Android theme)
    onSurface = DarkGreenGray90, // Color for text/icons on the surface (dark Android theme)
    surfaceVariant = GreenGray30, // Variation of the surface color (dark Android theme)
    onSurfaceVariant = GreenGray80, // Color for text/icons on the surfaceVariant (dark Android theme)
    inverseSurface = DarkGreenGray90, // Color for inverted elements (light text on dark backgrounds) (dark Android theme)
    inverseOnSurface = DarkGreenGray10, // Color for text/icons on the inverseSurface (dark Android theme)
    outline = GreenGray60,  // Color for borders, dividers or outlines (dark Android theme)
)

/**
 * Light Android gradient colors
 */
val LightAndroidGradientColors = GradientColors(container = DarkGreenGray95)

/**
 * Dark Android gradient colors
 */
val DarkAndroidGradientColors = GradientColors(container = Color.Black)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = DarkGreenGray95)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

/**
 * Now in Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */

object ProductXTheme {
    val backgroundTheme: BackgroundTheme
        @Composable
        get() = LocalBackgroundTheme.current

    val tintTheme: TintTheme
        @Composable
        get() = LocalTintTheme.current

    val colors: ColorTheme
        @Composable
        get() = LocalColorTheme.current

    val gradientColors: GradientColors
        @Composable
        get() = LocalGradientColors.current
}


@Composable
fun ProductXApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = when {
        androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
        !disableDynamicTheming && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }

    // Create ColorTheme object
    val colorTheme = when (colorScheme) {
        LightDefaultColorScheme -> ColorTheme(
            primary = LightDefaultColorScheme.primary,
            secondary = LightDefaultColorScheme.secondary,
            background = LightDefaultColorScheme.background,
            border = LightDefaultColorScheme.outline,
            floated = LightDefaultColorScheme.surface,
            textPrimary = LightDefaultColorScheme.onPrimary,
            textSecondary = LightDefaultColorScheme.onSecondary,
            textSurfaceVariant = LightDefaultColorScheme.onSurfaceVariant,
            textInteractive = LightDefaultColorScheme.onSurface,
            textLink = LightDefaultColorScheme.secondary,
            tornado1 = listOf(
                LightDefaultColorScheme.primary,
                LightDefaultColorScheme.secondary
            ),
            iconPrimary = LightDefaultColorScheme.primary,
            iconSecondary = LightDefaultColorScheme.secondary,
            iconInteractive = LightDefaultColorScheme.onSurface,
            iconInteractiveInactive = LightDefaultColorScheme.onSurfaceVariant,
            error = LightDefaultColorScheme.error,
            notificationBadge = LightDefaultColorScheme.error,
            isDark = false
        )

        DarkDefaultColorScheme -> ColorTheme(
            primary = DarkDefaultColorScheme.primary,
            secondary = DarkDefaultColorScheme.secondary,
            background = DarkDefaultColorScheme.background,
            border = DarkDefaultColorScheme.outline,
            floated = DarkDefaultColorScheme.surface,
            textPrimary = DarkDefaultColorScheme.onPrimary,
            textSecondary = DarkDefaultColorScheme.onSecondary,
            textSurfaceVariant = DarkDefaultColorScheme.onSurfaceVariant,
            textInteractive = DarkDefaultColorScheme.onSurface,
            textLink = DarkDefaultColorScheme.secondary,
            tornado1 = listOf(
                DarkDefaultColorScheme.primary,
                DarkDefaultColorScheme.secondary
            ),
            iconPrimary = DarkDefaultColorScheme.primary,
            iconSecondary = DarkDefaultColorScheme.secondary,
            iconInteractive = DarkDefaultColorScheme.onSurface,
            iconInteractiveInactive = DarkDefaultColorScheme.onSurfaceVariant,
            error = DarkDefaultColorScheme.error,
            notificationBadge = DarkDefaultColorScheme.error,
            isDark = true
        )

        LightAndroidColorScheme -> ColorTheme(
            primary = LightAndroidColorScheme.primary,
            secondary = LightAndroidColorScheme.secondary,
            background = LightAndroidColorScheme.background,
            border = LightAndroidColorScheme.outline,
            floated = LightAndroidColorScheme.surface,
            textPrimary = LightAndroidColorScheme.onPrimary,
            textSecondary = LightAndroidColorScheme.onSecondary,
            textSurfaceVariant = LightAndroidColorScheme.onSurfaceVariant,
            textInteractive = LightAndroidColorScheme.onSurface,
            textLink = LightAndroidColorScheme.secondary,
            tornado1 = listOf(
                LightAndroidColorScheme.primary,
                LightAndroidColorScheme.secondary
            ),
            iconPrimary = LightAndroidColorScheme.primary,
            iconSecondary = LightAndroidColorScheme.secondary,
            iconInteractive = LightAndroidColorScheme.onSurface,
            iconInteractiveInactive = LightAndroidColorScheme.onSurfaceVariant,
            error = LightAndroidColorScheme.error,
            notificationBadge = LightAndroidColorScheme.error,
            isDark = false
        )

        DarkAndroidColorScheme -> ColorTheme(
            primary = DarkAndroidColorScheme.primary,
            secondary = DarkAndroidColorScheme.secondary,
            background = DarkAndroidColorScheme.background,
            border = DarkAndroidColorScheme.outline,
            floated = DarkAndroidColorScheme.surface,
            textPrimary = DarkAndroidColorScheme.onPrimary,
            textSecondary = DarkAndroidColorScheme.onSecondary,
            textSurfaceVariant = DarkAndroidColorScheme.onSurfaceVariant,
            textInteractive = DarkAndroidColorScheme.onSurface,
            textLink = DarkAndroidColorScheme.secondary,
            tornado1 = listOf(
                DarkAndroidColorScheme.primary,
                DarkAndroidColorScheme.secondary
            ),
            iconPrimary = DarkAndroidColorScheme.primary,
            iconSecondary = DarkAndroidColorScheme.secondary,
            iconInteractive = DarkAndroidColorScheme.onSurface,
            iconInteractiveInactive = DarkAndroidColorScheme.onSurfaceVariant,
            error = DarkAndroidColorScheme.error,
            notificationBadge = DarkAndroidColorScheme.error,
            isDark = true
        )

        else -> ColorTheme(
            primary = colorScheme.primary,
            secondary = colorScheme.secondary,
            background = colorScheme.background,
            border = colorScheme.outline,
            floated = colorScheme.surface,
            textPrimary = colorScheme.onPrimary,
            textSecondary = colorScheme.onSecondary,
            textSurfaceVariant = colorScheme.onSurfaceVariant,
            textInteractive = colorScheme.onSurface,
            textLink = colorScheme.secondary,
            tornado1 = listOf(
                colorScheme.primary,
                colorScheme.secondary
            ),
            iconPrimary = colorScheme.primary,
            iconSecondary = colorScheme.secondary,
            iconInteractive = colorScheme.onSurface,
            iconInteractiveInactive = colorScheme.onSurfaceVariant,
            error = colorScheme.error,
            notificationBadge = colorScheme.error,
            isDark = darkTheme
        )
    }

    // Gradient colors
    val emptyGradientColors = GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )

    val gradientColors = when {
        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColors
        else -> defaultGradientColors
    }
    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }
    val tintTheme = when {
        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> TintTheme(colorScheme.primary)
        else -> TintTheme()
    }
    // Composition locals
    CompositionLocalProvider(
        LocalColorTheme provides colorTheme,
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
        content = {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                content = content,
            )
        }
    )
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
enum class DarkThemeConfig {
    FOLLOW_SYSTEM,
    LIGHT,
    DARK,
}

