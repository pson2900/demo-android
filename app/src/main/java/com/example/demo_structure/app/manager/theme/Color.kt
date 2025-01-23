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
import androidx.compose.runtime.staticCompositionLocalOf
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
        4 ->{
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
        8 ->{
            // Convert #RRGGBBAA to Int
            val colorInt = cleanHex.toLong(16)
            Color(colorInt)
        }
        else -> Color.Transparent // Return transparent if the hex code is invalid
    }
}


@Immutable
data class ColorTheme(
    val primary: Color, // The primary brand color of your application. Use this for main actions, highlights, or logo elements. For example: Primary button backgrounds, selected states.
    val secondary: Color, // A secondary brand color, used to provide visual variety and hierarchy to the user interface. For example: Secondary buttons or a secondary element highlight.
    val background: Color, // The background color for the main UI. Use it for the root container, activity background, etc. This color should make the other elements readable.
    val border: Color, // Color for borders, outlines, dividers, and separators in the UI. Use it to define boundaries and add structure. For example: Card borders or dividers in a list.
    val floated: Color, // The background color for elements that float above the main UI, like cards, modals, and dialogs. It should be visually distinct but also cohesive with the background.
    val textPrimary: Color = primary, // The primary color for important text and titles. It should have sufficient contrast with the background for good readability. If the value is not provided, it defaults to the brand color.
    val textSecondary: Color, // The color for less important text, such as subtitles, captions, or descriptions. Should have less contrast than the primary text, usually a lighter color.
    val textSurfaceVariant: Color, // Color for help text or hints that guide the user. This is usually less contrasting to be less prominent.
    val textInteractive: Color, // Color for text elements that users can interact with, like buttons, links, or text fields, to give them a visual cue.
    val textLink: Color, // Color for standalone links. In some UI designs is common to use a different color for links.
    val tornado1: List<Color>, // List of colors to be used in a gradient or multi-color effect. For example, you can use this list to create a gradient with `Brush.horizontalGradient`.
    val iconPrimary: Color = primary, // The primary color for icons, often used for key or most used icons in the UI. If the value is not provided, it defaults to the brand color.
    val iconSecondary: Color, // Secondary color for icons, often used for decorative or less important icons.
    val iconInteractive: Color, // Color for icons that respond to interactions, this color indicates the user the icon is interactive.
    val iconInteractiveInactive: Color, // Color for interactive icons when they are in an inactive state, this color indicates to the user that the icon is not actionable at the moment.
    val error: Color, // Color to represent error states or warnings, should have strong visual weight and be clearly noticeable, usually in red tones.
    val notificationBadge: Color = error, // Color for notification badges or indicators, to call the user's attention.  If the value is not provided, it defaults to the error color.
    val isDark: Boolean // Flag to determine the application is using a Dark or Light Theme, you can use this flag to create different styles based on the active theme.
)

@Immutable
data class ColorAppTheme(
    // Màu chính của ứng dụng, thường dùng cho các thành phần quan trọng như nút, thanh tiến trình, active states,...
    // Thường dùng cho các thành phần UI chính và tương tác được.
    val primary: Color,
    // Màu cho chữ hoặc icon khi hiển thị trên nền màu primary để đảm bảo độ tương phản.
    // Đảm bảo chữ hoặc icon dễ đọc và có thể tương tác rõ ràng.
    val onPrimary: Color,
    // Màu nền cho các container hoặc background liên quan đến màu primary.
    // Thường dùng để tạo vùng chứa trực quan, liên quan đến hành động chính.
    val primaryContainer: Color, // Ví dụ: Background cho một Button có ý nghĩa quan trọng
    // Màu cho chữ hoặc icon hiển thị trên background của primaryContainer.
    // Đảm bảo chữ/icon có thể nhìn rõ trên background.
    val onPrimaryContainer: Color, // Ví dụ: Màu chữ trên một Button có ý nghĩa quan trọng.
    // Màu primary "đảo ngược", dùng khi cần các element màu primary nổi bật hơn trên nền tối.
    // Tạo ra màu sắc thay thế cho thành phần primary, khi ở trên một nền có màu tương tự,
    val inversePrimary: Color, // Ví dụ: Màu nền cho một button khi nằm trên một background sáng.
    // Màu phụ của ứng dụng, dùng cho các phần ít quan trọng hơn.
    // Thường dùng cho các button phụ, các phần ít nổi bật hơn.
    val secondary: Color, // Ví dụ: Màu cho nút "Hủy"
    // Màu cho chữ hoặc icon khi hiển thị trên màu secondary.
    // Đảm bảo chữ/icon có thể nhìn rõ trên màu secondary.
    val onSecondary: Color, // Ví dụ: Màu chữ trên nút "Hủy"
    // Màu nền cho container liên quan đến màu secondary.
    // Tạo ra background liên quan đến hành động phụ.
    val secondaryContainer: Color, // Ví dụ: Background nhạt cho vùng chứa các tùy chọn phụ.
    // Màu cho chữ hoặc icon khi hiển thị trên background của secondaryContainer.
    // Đảm bảo chữ/icon dễ nhìn trên background này.
    val onSecondaryContainer: Color, // Ví dụ: Màu chữ trên vùng chứa các tùy chọn phụ.
    // Màu thứ 3, thường dùng cho các điểm nhấn hoặc chi tiết ít quan trọng.
    // Thường dùng để tạo điểm nhấn cho UI.
    val tertiary: Color, // Ví dụ: Màu cho icon trong bottom navigation.
    // Màu cho chữ hoặc icon khi hiển thị trên màu tertiary.
    //  Đảm bảo chữ hoặc icon có thể nhìn rõ trên background này.
    val onTertiary: Color, // Ví dụ: Màu cho icon khi không được chọn trong bottom navigation.
    // Màu nền cho container liên quan đến màu tertiary.
    // Thường dùng để tạo background liên quan đến các điểm nhấn.
    val tertiaryContainer: Color, // Ví dụ: Background cho icon trong bottom navigation khi item được chọn.
    // Màu cho chữ hoặc icon khi hiển thị trên background của tertiaryContainer.
    //  Đảm bảo chữ hoặc icon có thể nhìn rõ trên background này.
    val onTertiaryContainer: Color, // Ví dụ: Màu cho icon được chọn trong bottom navigation.
    // Màu nền chính của toàn bộ ứng dụng, hoặc khu vực chính của UI.
    // Sử dụng cho toàn bộ màn hình, panel, dialog, hoặc các phần UI lớn.
    val background: Color, // Ví dụ: Màu nền cho toàn bộ app
    // Màu cho chữ hoặc icon khi hiển thị trên background của ứng dụng.
    // Đảm bảo chữ hoặc icon có thể nhìn rõ trên nền background.
    val onBackground: Color, // Ví dụ: Màu chữ cho toàn bộ app.
    // Màu cho các bề mặt như cards, panels, bottom sheet,...
    // Sử dụng cho các thành phần như cards, panel,...
    val surface: Color, // Ví dụ: Màu nền cho card.
    // Màu cho chữ hoặc icon khi hiển thị trên bề mặt surface.
    // Đảm bảo chữ hoặc icon có thể nhìn rõ trên bề mặt surface.
    val onSurface: Color, // Ví dụ: Màu chữ trên card.
    // Một biến thể (variation) của màu surface, dùng cho các phần con hoặc ít nổi bật hơn.
    // Thường dùng cho header, hoặc subcomponent của `surface`
    val surfaceVariant: Color, // Ví dụ: Màu nền cho header của card.
    // Màu cho chữ hoặc icon hiển thị trên background surfaceVariant.
    // Đảm bảo chữ/icon có thể nhìn rõ trên biến thể của surface
    val onSurfaceVariant: Color, // Ví dụ: Màu chữ trên header của card.
    // Màu dùng làm màu nền khi tint một component
    // Dùng khi tint một element
    val surfaceTint: Color,
    // Màu cho bề mặt "đảo ngược", (ví dụ: chữ sáng trên nền tối).
    // Thường dùng cho các dialog, bottom sheet, hoặc các element quan trọng muốn nổi bật trên nền tối.
    val inverseSurface: Color,
    // Màu cho chữ hoặc icon khi hiển thị trên màu inverseSurface.
    // Đảm bảo chữ/icon có độ tương phản tốt trên màu `inverseSurface`.
    val inverseOnSurface: Color,
    // Màu dùng để hiển thị lỗi, như message lỗi, validation form,...
    // Dùng cho các thông báo lỗi, các UI feedback liên quan đến lỗi
    val error: Color, // Ví dụ: Màu đỏ cho các thông báo lỗi.
    // Màu cho chữ hoặc icon khi hiển thị trên màu error.
    // Đảm bảo chữ/icon có độ tương phản tốt trên màu `error`.
    val onError: Color, // Ví dụ: Màu chữ trên thông báo lỗi.
    // Màu nền cho các container chứa thông báo lỗi hoặc liên quan đến lỗi.
    // Dùng cho các container chứa các component báo lỗi.
    val errorContainer: Color, // Ví dụ: Background đỏ nhạt cho vùng chứa thông báo lỗi.
    // Màu cho chữ hoặc icon hiển thị trên background của errorContainer.
    // Đảm bảo chữ/icon có độ tương phản tốt trên vùng chứa thông báo lỗi.
    val onErrorContainer: Color, // Ví dụ: Màu chữ đậm hơn trên background chứa thông báo lỗi.
    // Màu cho các đường viền, đường phân cách, outline.
    // Dùng cho các outline của các component.
    val outline: Color, // Ví dụ: Màu viền của TextField
    // Biến thể của màu outline, thường dùng cho các outline yếu hơn.
    // Dùng khi muốn tạo các outline ít nổi bật hơn.
    val outlineVariant: Color, // Ví dụ: Màu viền của textfield khi không focus
    // Màu dùng cho các scrim, như khi hiển thị bottom sheet hoặc các dialog.
    // Màu của scrim của bottomsheet, hoặc các element mà cần che phủ nội dung bên dưới.
    val scrim: Color, // Ví dụ: Màu overlay khi mở bottom sheet.
    // Màu bề mặt sáng
    // Màu sắc của các layer bề mặt sáng
    val surfaceBright: Color, // Màu sắc surface sáng.
    // Màu bề mặt mờ
    // Màu sắc của các layer bề mặt mờ
    val surfaceDim: Color, // Màu sắc surface mờ
    // Màu container surface
    // Dùng cho các background của các container
    val surfaceContainer: Color, // Background của các container
    // Màu container surface cao hơn
    // Dùng cho background của các container có độ nổi bật cao hơn
    val surfaceContainerHigh: Color, // Background container có độ nổi bật cao
    // Màu container surface cao nhất
    // Dùng cho background của các container có độ nổi bật cao nhất
    val surfaceContainerHighest: Color, // Background container có độ nổi bật cao nhất
    // Màu container surface thấp hơn
    // Dùng cho background của các container ít nổi bật hơn
    val surfaceContainerLow: Color, // Background container có độ nổi bật thấp
    // Màu container surface thấp nhất
    // Dùng cho background của các container ít nổi bật nhất
    val surfaceContainerLowest: Color, // Background container có độ nổi bật thấp nhất
    // Flag để xác định ứng dụng đang dùng Dark Theme hay Light Theme.
    // Dùng để tạo logic phân biệt theme Dark/Light.
    val isDark: Boolean
)


/*


internal val Blue10 = Color(0xFF001F28)
internal val Blue20 = Color(0xFF003544)
internal val Blue30 = Color(0xFF004D61)
internal val Blue40 = Color(0xFF006780)
internal val Blue80 = Color(0xFF5DD5FC)
internal val Blue90 = Color(0xFFB8EAFF)
internal val DarkGreen10 = Color(0xFF0D1F12)
internal val DarkGreen20 = Color(0xFF223526)
internal val DarkGreen30 = Color(0xFF394B3C)
internal val DarkGreen40 = Color(0xFF4F6352)
internal val DarkGreen80 = Color(0xFFB7CCB8)
internal val DarkGreen90 = Color(0xFFD3E8D3)
internal val DarkGreenGray10 = Color(0xFF1A1C1A)
internal val DarkGreenGray20 = Color(0xFF2F312E)
internal val DarkGreenGray90 = Color(0xFFE2E3DE)
internal val DarkGreenGray95 = Color(0xFFF0F1EC)
internal val DarkGreenGray99 = Color(0xFFFBFDF7)
internal val DarkPurpleGray10 = Color(0xFF201A1B)
internal val DarkPurpleGray20 = Color(0xFF362F30)
internal val DarkPurpleGray90 = Color(0xFFECDFE0)
internal val DarkPurpleGray95 = Color(0xFFFAEEEF)
internal val DarkPurpleGray99 = Color(0xFFFCFCFC)
internal val Green10 = Color(0xFF00210B)
internal val Green20 = Color(0xFF003919)
internal val Green30 = Color(0xFF005227)
internal val Green40 = Color(0xFF006D36)
internal val Green80 = Color(0xFF0EE37C)
internal val Green90 = Color(0xFF5AFF9D)
internal val GreenGray30 = Color(0xFF414941)
internal val GreenGray50 = Color(0xFF727971)
internal val GreenGray60 = Color(0xFF8B938A)
internal val GreenGray80 = Color(0xFFC1C9BF)
internal val GreenGray90 = Color(0xFFDDE5DB)
internal val Orange10 = Color(0xFF380D00)
internal val Orange20 = Color(0xFF5B1A00)
internal val Orange30 = Color(0xFF812800)
internal val Orange40 = Color(0xFFA23F16)
internal val Orange80 = Color(0xFFFFB59B)
internal val Orange90 = Color(0xFFFFDBCF)
internal val Purple10 = Color(0xFF36003C)
internal val Purple20 = Color(0xFF560A5D)
internal val Purple30 = Color(0xFF702776)
internal val Purple40 = Color(0xFF8B418F)
internal val Purple80 = Color(0xFFFFA9FE)
internal val Purple90 = Color(0xFFFFD6FA)
internal val PurpleGray30 = Color(0xFF4D444C)
internal val PurpleGray50 = Color(0xFF7F747C)
internal val PurpleGray60 = Color(0xFF998D96)
internal val PurpleGray80 = Color(0xFFD0C3CC)
internal val PurpleGray90 = Color(0xFFEDDEE8)
internal val Red10 = Color(0xFF410002)
internal val Red20 = Color(0xFF690005)
internal val Red30 = Color(0xFF93000A)
internal val Red40 = Color(0xFFBA1A1A)
internal val Red80 = Color(0xFFFFB4AB)
internal val Red90 = Color(0xFFFFDAD6)
internal val Teal10 = Color(0xFF001F26)
internal val Teal20 = Color(0xFF02363F)
internal val Teal30 = Color(0xFF214D56)
internal val Teal40 = Color(0xFF3A656F)
internal val Teal80 = Color(0xFFA2CED9)
internal val Teal90 = Color(0xFFBEEAF6)


val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Teal700 = Color(0xFF018786)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val Primary80 = Color(0xFFA0C1FF) // Assuming you want it to be opaque
val Primary50 = Color(0xFFEFF5FF) // Assuming you want it to be opaque
val MistyRose = Color(0xFFFFECE6)
val Coral = Color(0xFFFF7D55) // Assuming you want it to be opaque
val GhostWhite = Color(0xFFF9FBFF) // Assuming you want it to be opaque
val Linen = Color(0xFFFFECE6)




val Shadow11 = Color(0xff001787)
val Shadow10 = Color(0xff00119e)
val Shadow9 = Color(0xff0009b3)
val Shadow8 = Color(0xff0200c7)
val Shadow7 = Color(0xff0e00d7)
val Shadow6 = Color(0xff2a13e4)
val Shadow5 = Color(0xff4b30ed)
val Shadow4 = Color(0xff7057f5)
val Shadow3 = Color(0xff9b86fa)
val Shadow2 = Color(0xffc8bbfd)
val Shadow1 = Color(0xffded6fe)
val Shadow0 = Color(0xfff4f2ff)

val Ocean11 = Color(0xff005687)
val Ocean10 = Color(0xff006d9e)
val Ocean9 = Color(0xff0087b3)
val Ocean8 = Color(0xff00a1c7)
val Ocean7 = Color(0xff00b9d7)
val Ocean6 = Color(0xff13d0e4)
val Ocean5 = Color(0xff30e2ed)
val Ocean4 = Color(0xff57eff5)
val Ocean3 = Color(0xff86f7fa)
val Ocean2 = Color(0xffbbfdfd)
val Ocean1 = Color(0xffd6fefe)
val Ocean0 = Color(0xfff2ffff)

val Lavender11 = Color(0xff170085)
val Lavender10 = Color(0xff23009e)
val Lavender9 = Color(0xff3300b3)
val Lavender8 = Color(0xff4400c7)
val Lavender7 = Color(0xff5500d7)
val Lavender6 = Color(0xff6f13e4)
val Lavender5 = Color(0xff8a30ed)
val Lavender4 = Color(0xffa557f5)
val Lavender3 = Color(0xffc186fa)
val Lavender2 = Color(0xffdebbfd)
val Lavender1 = Color(0xffebd6fe)
val Lavender0 = Color(0xfff9f2ff)

val Rose11 = Color(0xff7f0054)
val Rose10 = Color(0xff97005c)
val Rose9 = Color(0xffaf0060)
val Rose8 = Color(0xffc30060)
val Rose7 = Color(0xffd4005d)
val Rose6 = Color(0xffe21365)
val Rose5 = Color(0xffec3074)
val Rose4 = Color(0xfff4568b)
val Rose3 = Color(0xfff985aa)
val Rose2 = Color(0xfffdbbcf)
val Rose1 = Color(0xfffed6e2)
val Rose0 = Color(0xfffff2f6)

val Neutral8 = Color(0xff121212)
val Neutral7 = Color(0xde000000)
val Neutral6 = Color(0x99000000)
val Neutral5 = Color(0x61000000)
val Neutral4 = Color(0x1f000000)
val Neutral3 = Color(0x1fffffff)
val Neutral2 = Color(0x61ffffff)
val Neutral1 = Color(0xbdffffff)
val Neutral0 = Color(0xffffffff)

val FunctionalRed = Color(0xffd00036)
val FunctionalRedDark = Color(0xffea6d7e)
val FunctionalGreen = Color(0xff52c41a)
val FunctionalGrey = Color(0xfff6f6f6)
val FunctionalDarkGrey = Color(0xff2e2e2e)

const val AlphaNearOpaque = 0.95f
*/
