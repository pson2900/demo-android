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

