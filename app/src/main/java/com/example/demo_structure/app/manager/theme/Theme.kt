package com.example.demo_structure.app.manager.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Light default theme color scheme
 */

@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = hexToColor("#775AFF"), // The primary color of the app, e.g., for main buttons
    onPrimary = hexToColor("#000000"), // Color for text/icons on the primary color
    primaryContainer = hexToColor("#775AFF"), // Background color for containers based on primary
    onPrimaryContainer = hexToColor("#775AFF"), // Color for text/icons on the primaryContainer
    secondary = hexToColor("#F1F5F9"), // Secondary color, used for less important parts
    onSecondary = hexToColor("#000000"), // Color for text/icons on the secondary color
    secondaryContainer = hexToColor("#FFFFFF"), // Background color for containers based on secondary
    onSecondaryContainer = hexToColor("#000000"), // Color for text/icons on the secondaryContainer
    tertiary = hexToColor("#775AFF"), // Tertiary color, used for accents or small details
    onTertiary = hexToColor("#94A3B8"), // Color for text/icons on the tertiary color
    tertiaryContainer = hexToColor("#000000"), // Background color for containers based on tertiary
    onTertiaryContainer = hexToColor("#000000"), // Color for text/icons on the tertiaryContainer
    error = hexToColor("#EC221F"), // Color for errors, e.g., error messages
    onError = hexToColor("#EC221F"), // Color for text/icons on the error color
    errorContainer = hexToColor("#EC221F"), // Background color for containers containing errors
    onErrorContainer = hexToColor("#EC221F"), // Color for text/icons on the errorContainer
    background = Color.White, // Background color of the entire app
    onBackground = hexToColor("#000000"), // Color for text/icons on the background
    surface = hexToColor("#F1F5F9"), // Color for surfaces like cards or bottom sheets
    onSurface = hexToColor("#000000"), // Color for text/icons on the surface
    surfaceVariant = hexToColor("#FFFFFF"), // Variation of the surface color, for subparts
    onSurfaceVariant = hexToColor("#000000"), // Color for text/icons on the surfaceVariant
    inverseSurface = hexToColor("#FFFFFF"), // Color for inverted elements (light text on dark backgrounds)
    inverseOnSurface = hexToColor("#000000"), // Color for text/icons on the inverseSurface
    outline = hexToColor("#000000"), // Color for borders, dividers or outlines
    outlineVariant = hexToColor("#000000"), // Color for borders, dividers or outlines on surfaces
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = hexToColor("#775AFF"), // The primary color of the app (dark theme version)
    onPrimary = hexToColor("#FFFFFF"), // Color for text/icons on the primary color (dark theme)
    primaryContainer = hexToColor("#775AFF"), // Background color for containers based on primary (dark theme)
    onPrimaryContainer = hexToColor("#775AFF"), // Color for text/icons on the primaryContainer (dark theme)
    secondary = hexToColor("#000000"), // Secondary color (dark theme)
    onSecondary = hexToColor("#FFFFFF"), // Color for text/icons on the secondary color (dark theme)
    secondaryContainer = hexToColor("#000000"), // Background color for containers based on secondary (dark theme)
    onSecondaryContainer = hexToColor("#FFFFFF"), // Color for text/icons on the secondaryContainer (dark theme)
    tertiary = hexToColor("#775AFF"), // Tertiary color (dark theme)
    onTertiary = hexToColor("#94A3B8"), // Color for text/icons on the tertiary color (dark theme)
    tertiaryContainer = hexToColor("#FFFFFF"), // Background color for containers based on tertiary (dark theme)
    onTertiaryContainer = hexToColor("#FFFFFF"), // Color for text/icons on the tertiaryContainer (dark theme)
    error = hexToColor("#EC221F"), // Color for errors (dark theme)
    onError = hexToColor("#EC221F"), // Color for text/icons on the error color (dark theme)
    errorContainer = hexToColor("#EC221F"), // Background color for containers containing errors (dark theme)
    onErrorContainer = hexToColor("#EC221F"), // Color for text/icons on the errorContainer (dark theme)
    background = Color.Black, // Background color of the entire app
    onBackground = Color.White, // Color for text/icons on the background
    surface = hexToColor("#000000"), // Color for surfaces like cards or bottom sheets
    onSurface = Color.White, // Color for text/icons on the surface
    surfaceVariant = hexToColor("#000000"), // Variation of the surface color (dark theme)
    onSurfaceVariant = Color.White, // Color for text/icons on the surfaceVariant (dark theme)
    inverseSurface = hexToColor("#000000"), // Color for inverted elements (light text on dark backgrounds) (dark theme)
    inverseOnSurface = hexToColor("#FFFFFF"), // Color for text/icons on the inverseSurface (dark theme)
    outline = hexToColor("#FFFFFF"), // Color for borders, dividers or outlines (dark theme)
    outlineVariant = hexToColor("#FFFFFF"), // Color for borders, dividers or outlines on surfaces
)

/**
 * Now in Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
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

    val colorScheme: ColorScheme
        @Composable
        get() = LocalColorTheme.current

    val gradientColors: GradientColors
        @Composable
        get() = LocalGradientColors.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}


@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    // Cập nhật màu status bar dựa trên chế độ sáng/tối
    LaunchedEffect(darkTheme) {
        systemUiController.setStatusBarColor(
            color = if (darkTheme) Color.Black else Color.White,
            darkIcons = !darkTheme
        )
    }
    // Color scheme
    val colorScheme = if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme


    // Gradient colors
    val emptyGradientColors = GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )

    val gradientColors = when {
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColors
        else -> defaultGradientColors
    }
    // Background theme
    val backgroundTheme = BackgroundTheme(
        color = colorScheme.background,
        tonalElevation = 2.dp,
    )
    val cardTheme = CardTheme(
        background = colorScheme.surface,
        text = colorScheme.onSurface,
        colorBackgroundHeader = colorScheme.surfaceVariant,
        colorTextHeader = colorScheme.onSurfaceVariant,
        colorOutline = colorScheme.outline,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(10.dp, Color.Unspecified),
    )

    // Composition locals
    CompositionLocalProvider(
        LocalColorTheme provides colorScheme,
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
        LocalCardTheme provides cardTheme,

        content = {
            MaterialTheme(
                colorScheme = colorScheme,
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