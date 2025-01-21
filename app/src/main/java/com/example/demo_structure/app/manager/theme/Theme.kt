package com.example.demo_structure.app.manager.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
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
    primary = hexToColor("#775AFF"), // The primary color of the app, e.g., for main buttons
    onPrimary = Color.Black, // Color for text/icons on the primary color
    primaryContainer = Purple90, // Background color for containers based on primary
    onPrimaryContainer = Purple10, // Color for text/icons on the primaryContainer
    secondary = Orange40, // Secondary color, used for less important parts
    onSecondary = Color.Black, // Color for text/icons on the secondary color
    secondaryContainer = Orange90, // Background color for containers based on secondary
    onSecondaryContainer = Orange10, // Color for text/icons on the secondaryContainer
    tertiary = hexToColor("#775AFF"), // Tertiary color, used for accents or small details
    onTertiary = hexToColor("#94A3B8"), // Color for text/icons on the tertiary color
    tertiaryContainer = Blue90, // Background color for containers based on tertiary
    onTertiaryContainer = Blue10, // Color for text/icons on the tertiaryContainer
    error = Red40, // Color for errors, e.g., error messages
    onError = Color.Red, // Color for text/icons on the error color
    errorContainer = Red90, // Background color for containers containing errors
    onErrorContainer = Red10, // Color for text/icons on the errorContainer
    background = hexToColor("#F1F5F9"), // Background color of the entire app
    onBackground = hexToColor("#000000"), // Color for text/icons on the background
    surface = hexToColor("#FFFFFF"), // Color for surfaces like cards or bottom sheets
    onSurface = hexToColor("#000000"), // Color for text/icons on the surface
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
    background = hexToColor("#000000"), // Background color of the entire app
    onBackground = hexToColor("#FFFFFF"), // Color for text/icons on the background
    surface = hexToColor("#000000"), // Color for surfaces like cards or bottom sheets
    onSurface = hexToColor("#FFFFFF"), // Color for text/icons on the surface
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
    background = hexToColor("#F1F5F9"), // Background color of the entire app
    onBackground = hexToColor("#000000"), // Color for text/icons on the background
    surface = hexToColor("#FFFFFF"), // Color for surfaces like cards or bottom sheets
    onSurface = hexToColor("#000000"), // Color for text/icons on the surface
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

    val cardTheme: CardTheme
        @Composable
        get() = LocalCardTheme.current

    val colors: ColorScheme
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
        /*androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
        !disableDynamicTheming && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme*/
        !disableDynamicTheming && supportsDynamicTheming() -> {
            if (darkTheme) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(LocalContext.current)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }


    // Gradient colors
    val emptyGradientColors = GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )

    val gradientColors = when {
//        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColors
        else -> defaultGradientColors
    }
    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.background,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
//        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }
    val cardTheme = when {
//        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> CardTheme(colorScheme.primary)
        else -> CardTheme(
            colorBackground = colorScheme.surface,
            colorText = colorScheme.onSurface,
            colorBackgroundHeader = colorScheme.surfaceVariant,
            colorTextHeader = colorScheme.onSurfaceVariant,
            colorOutline = colorScheme.outline
        )
    }
    // Composition locals
    CompositionLocalProvider(
        LocalColorTheme provides colorScheme,
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalCardTheme provides cardTheme,
        content = {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = appTypography,
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


/**
 * --- Tổng quan về các nhóm option màu ---
 *
 *  * `primary`, `secondary`, `tertiary`: Màu sắc chính của ứng dụng, được dùng cho các element có ý nghĩa quan trọng và cần sự nổi bật
 *    * `on...`: Màu sắc của chữ hoặc icon trên các element có màu tương ứng
 *    * `...Container`: Màu nền của các container có liên quan đến màu tương ứng
 *    *  `on...Container`: Màu sắc của chữ hoặc icon trên các container có màu nền tương ứng
 *  * `surface`, `inverseSurface`: Màu sắc cho các vùng hiển thị thông tin, nội dung trên UI.
 *    * `on...`: Màu sắc của chữ hoặc icon trên các surface
 *   `background`: Màu nền của ứng dụng, các màn hình,...
 *   * `onBackground`: Màu sắc của chữ hoặc icon trên các background
 *   * `outline`: Màu của đường viền hoặc các element không được chọn
 *   * `scrim`: Màu của lớp phủ khi một element được hiển thị phía trên một layer khác.
 *  * `error`: Màu sắc để hiển thị lỗi.
 *     * `on...` Màu sắc của chữ hoặc icon khi hiển thị trên màu error
 *     * `...Container`: Màu nền của container khi có liên quan đến lỗi
 *      * `on...Container`: Màu sắc của chữ hoặc icon trên container có màu lỗi.
 * --- Các option màu cho từng View ---
 *
 * Button:
 *  primary: Màu nền chính của nút (khi ở trạng thái bình thường).
 *  onPrimary: Màu chữ hoặc icon trên nút.
 *  primaryContainer: Màu nền khi nút nằm trong vùng chứa có màu nền riêng.
 *
 * Text:
 *  onBackground: Màu chữ cho nội dung hiển thị trên nền ứng dụng chính.
 *  onSurface: Màu chữ cho nội dung hiển thị trên bề mặt (card, bottom sheet,...).
 *  onError: Màu chữ cho các thông báo lỗi.
 *
 * TextField:
 *  background: Màu nền của vùng nhập liệu.
 *  onSurface: Màu chữ nhập vào.
 *  outline: Màu đường viền.
 *
 * Card:
 *  surface: Màu nền chính của card.
 *  onSurface: Màu chữ hoặc icon trên card.
 *  surfaceVariant: Màu nền header hoặc phần phụ của card.
 *  onSurfaceVariant: Màu chữ/icon trên header của card.
 *  outline: Màu đường viền của card.
 *
 * AlertDialog:
 *  surface: Màu nền của dialog.
 *  onSurface: Màu chữ hoặc icon trong dialog.
 *  inverseSurface: Màu nền "đảo ngược" của dialog, dùng khi nổi bật.
 *  inverseOnSurface : Màu chữ/icon trên nền "đảo ngược" của dialog.
 *
 * NavigationBar / BottomNavigationBar:
 *  background: Màu nền của thanh điều hướng.
 *  onBackground: Màu chữ/icon hiển thị trên thanh điều hướng
 *  primary: Màu cho icon/label khi item được chọn.
 *  onPrimary: Màu chữ/icon khi item không được chọn (trạng thái bình thường).
 *  tertiary: Màu cho icon/label khi có sự nổi bật.
 *  onTertiary: Màu cho các icon/label khi không được chọn.
 *  surface: Màu nền khi thanh điều hướng được tint.
 *
 * Snackbar:
 *  surface: Màu nền của snackbar.
 *  onSurface: Màu chữ/icon trong snackbar.
 *  inverseSurface: Màu nền "đảo ngược" của snackbar (ví dụ khi quan trọng).
 *  inverseOnSurface : Màu chữ/icon trên nền "đảo ngược" của snackbar.
 *
 * Drawer:
 *  surface: Màu nền chính của drawer.
 *  onSurface: Màu chữ/icon trong drawer.
 *  background: Màu nền của background drawer.
 *  onBackground: Màu chữ/icon trên background drawer.
 *  scrim: Màu lớp phủ (overlay) khi drawer mở.
 *
 * Checkbox:
 *  primary: Màu nền của checkbox khi được chọn.
 *  onPrimary: Màu của icon check trong checkbox khi được chọn.
 *  surface: Màu nền của checkbox khi không được chọn.
 *  outline: Màu của đường viền checkbox khi không được chọn.
 *
 * TabBar (TabRow / Tab):
 *  background: Màu nền của toàn bộ tab bar.
 *  primary: Màu nền của tab khi được chọn.
 *  onPrimary: Màu chữ/icon trong tab được chọn.
 *  surface: Màu nền của tab khi không được chọn.
 *  onSurface: Màu chữ/icon trong tab không được chọn.
 *  surfaceVariant: Màu của indicator (ví dụ: gạch chân) khi tab được chọn.
 *
 * NavigationBarItem:
 *  primary: Màu cho icon/label của item khi được chọn.
 *  onPrimary: Màu chữ/icon của item khi không được chọn.
 *  background: Màu nền của NavigationBarItem khi được chọn
 *  tertiary: Màu cho icon/label của item khi có độ nổi bật.
 *  onTertiary: Màu chữ/icon của item khi không được chọn.
 *
 * Surface:
 *   surface: Màu nền cho các bề mặt (card, panel, bottom sheet).
 *   onSurface: Màu chữ/icon trên surface.
 *
 * Background:
 *  background: Màu nền chính của ứng dụng/khu vực UI.
 *  onBackground: Màu chữ/icon trên nền ứng dụng chính.
 * */