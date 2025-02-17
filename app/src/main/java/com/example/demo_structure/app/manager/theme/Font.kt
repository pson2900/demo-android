package com.example.demo_structure.app.manager.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R

/**
 * Created by Phạm Sơn at 11:17/3/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
internal enum class FontDefinition(val font: Int, val weight: FontWeight, val style: FontStyle) {
    Regular(R.font.nunito_regular, FontWeight.W400, FontStyle.Normal),
    RegularItalic(R.font.nunito_italic, FontWeight.W400, FontStyle.Italic),

    Medium(R.font.nunito_medium, FontWeight.W500, FontStyle.Normal),
    MediumItalic(R.font.nunito_medium_italic, FontWeight.W500, FontStyle.Italic),

    SemiBold(R.font.nunito_semibold, FontWeight.W600, FontStyle.Normal),
    SemiBoldItalic(R.font.nunito_semi_bold_italic, FontWeight.W600, FontStyle.Italic),

    Bold(R.font.nunito_bold, FontWeight.W700, FontStyle.Normal),
    BoldItalic(R.font.nunito_bold_italic, FontWeight.W700, FontStyle.Italic),

    ExtraBold(R.font.nunito_extra_bold, FontWeight.W800, FontStyle.Normal),
    ExtraBoldItalic(R.font.nunito_extra_bold_italic, FontWeight.W800, FontStyle.Italic)
}


internal class BaseFont(private val font: FontDefinition) {

    fun toTextStyle(size: TextUnit, lineHeight: TextUnit) =
        TextStyle(fontFamily = FontFamily(Font(font.font)), fontSize = size, lineHeight = lineHeight, fontWeight = font.weight, fontStyle = font.style)

    internal inner class DisplayStyle {
        val X_Large: TextStyle by lazy { toTextStyle(eDisplay.X_Large.size, eDisplay.X_Large.lineHeight) }
        val Large: TextStyle by lazy { toTextStyle(eDisplay.Large.size, eDisplay.Large.lineHeight) }
        val Medium: TextStyle by lazy { toTextStyle(eDisplay.Medium.size, eDisplay.Medium.lineHeight) }
        val Small: TextStyle by lazy { toTextStyle(eDisplay.Small.size, eDisplay.Small.lineHeight) }
    }

    inner class HeadingStyle {
        val XX_Large: TextStyle by lazy { toTextStyle(eHeading.XX_Large.size, eHeading.XX_Large.lineHeight) }
        val X_Large: TextStyle by lazy { toTextStyle(eHeading.X_Large.size, eHeading.X_Large.lineHeight) }
        val Large: TextStyle by lazy { toTextStyle(eHeading.Large.size, eHeading.Large.lineHeight) }
        val Medium: TextStyle by lazy { toTextStyle(eHeading.Medium.size, eHeading.Medium.lineHeight) }
        val Small: TextStyle by lazy { toTextStyle(eHeading.Small.size, eHeading.Small.lineHeight) }
        val X_Small: TextStyle by lazy { toTextStyle(eHeading.X_Small.size, eHeading.X_Small.lineHeight) }
    }

    inner class TitleStyle {
        val X_Large: TextStyle by lazy { toTextStyle(eTitle.X_Large.size, eTitle.X_Large.lineHeight) }
        val Large: TextStyle by lazy { toTextStyle(eTitle.Large.size, eTitle.Large.lineHeight) }
        val Medium: TextStyle by lazy { toTextStyle(eTitle.Medium.size, eTitle.Medium.lineHeight) }
        val Small: TextStyle by lazy { toTextStyle(eTitle.Small.size, eTitle.Small.lineHeight) }
    }

    inner class LabelStyle {
        val X_Large: TextStyle by lazy { toTextStyle(eLabel.X_Large.size, eLabel.X_Large.lineHeight) }
        val Large: TextStyle by lazy { toTextStyle(eLabel.Large.size, eLabel.Large.lineHeight) }
        val Medium: TextStyle by lazy { toTextStyle(eLabel.Medium.size, eLabel.Medium.lineHeight) }
        val Small: TextStyle by lazy { toTextStyle(eLabel.Small.size, eLabel.Small.lineHeight) }
        val X_Small: TextStyle by lazy { toTextStyle(eLabel.X_Small.size, eLabel.X_Small.lineHeight) }
    }

    inner class BodyStyle {
        val X_Large: TextStyle by lazy { toTextStyle(eBody.X_Large.size, eBody.X_Large.lineHeight) }
        val Large: TextStyle by lazy { toTextStyle(eBody.Large.size, eBody.Large.lineHeight) }
        val Medium: TextStyle by lazy { toTextStyle(eBody.Medium.size, eBody.Medium.lineHeight) }
        val Small: TextStyle by lazy { toTextStyle(eBody.Small.size, eBody.Small.lineHeight) }
        val X_Small: TextStyle by lazy { toTextStyle(eBody.X_Small.size, eBody.X_Small.lineHeight) }
    }

    val Display = DisplayStyle()
    val Heading = HeadingStyle()
    val Title = TitleStyle()
    val Label = LabelStyle()
    val Body = BodyStyle()

    private enum class eDisplay(val size: TextUnit, val lineHeight: TextUnit) {
        X_Large(size = 96.sp, lineHeight = 112.sp),
        Large(size = 52.sp, lineHeight = 64.sp),
        Medium(size = 44.sp, lineHeight = 52.sp),
        Small(size = 36.sp, lineHeight = 44.sp),
    }

    private enum class eHeading(val size: TextUnit, val lineHeight: TextUnit) {
        XX_Large(size = 40.sp, lineHeight = 52.sp),
        X_Large(size = 36.sp, lineHeight = 44.sp),
        Large(size = 32.sp, lineHeight = 40.sp),
        Medium(size = 28.sp, lineHeight = 36.sp),
        Small(size = 24.sp, lineHeight = 32.sp),
        X_Small(size = 20.sp, lineHeight = 28.sp),
    }

    private enum class eTitle(val size: TextUnit, val lineHeight: TextUnit) {
        X_Large(size = 20.sp, lineHeight = 28.sp),
        Large(size = 18.sp, lineHeight = 28.sp),
        Medium(size = 16.sp, lineHeight = 24.sp),
        Small(size = 14.sp, lineHeight = 20.sp),
    }

    private enum class eLabel(val size: TextUnit, val lineHeight: TextUnit) {
        X_Large(size = 18.sp, lineHeight = 28.sp),
        Large(size = 16.sp, lineHeight = 24.sp),
        Medium(size = 14.sp, lineHeight = 20.sp),
        Small(size = 12.sp, lineHeight = 16.sp),
        X_Small(size = 10.sp, lineHeight = 14.sp),
    }

    private enum class eBody(val size: TextUnit, val lineHeight: TextUnit) {
        X_Large(size = 18.sp, lineHeight = 28.sp),
        Large(size = 16.sp, lineHeight = 24.sp),
        Medium(size = 14.sp, lineHeight = 20.sp),
        Small(size = 12.sp, lineHeight = 16.sp),
        X_Small(size = 10.sp, lineHeight = 14.sp),
    }
//    SemiItalicTitle-Large
//    SemiBOLDTitle-Large
//    SemiEXTtraboldTitle-Large
//    SemiITalicTitle-Large

//    SemiBOLDTitle-Large
//    SemiREGULARTitle-Large
//    SemiBOLDTitle-Large
//    SemiEXTRABOLDTitle-Large
//    SemiEXTRAREgularTitle-Large
//    SemiItalicTitle-Large

//    SemiItalicTitle-Large
//    SemiItalicTitle-Large
//    SemiItalicDisplay-Large

//  SemiItalic.Display.Large
//  SemiItalic.Title.Large
//  SemiItalic.Label.Large
}
val defaultTypography = Typography(
    displayLarge = BaseFont(FontDefinition.SemiBold).Title.Large,
    displayMedium = BaseFont(FontDefinition.SemiBold).Title.Medium,
    displaySmall = BaseFont(FontDefinition.SemiBold).Title.Small,
    headlineLarge = BaseFont(FontDefinition.SemiBold).Title.Large,
    headlineMedium = BaseFont(FontDefinition.SemiBold).Title.Medium,
    headlineSmall = BaseFont(FontDefinition.SemiBold).Title.Small,
    titleLarge =   BaseFont(FontDefinition.Bold).Title.Large,
    titleMedium = BaseFont(FontDefinition.Bold).Title.Medium,
    titleSmall = BaseFont(FontDefinition.Bold).Title.Small,
    bodyLarge = BaseFont(FontDefinition.Regular).Title.Large,
    bodyMedium = BaseFont(FontDefinition.Regular).Title.Medium,
    bodySmall = BaseFont(FontDefinition.Regular).Title.Small,
    labelLarge = BaseFont(FontDefinition.Medium).Title.Large,
    labelMedium = BaseFont(FontDefinition.Medium).Title.Medium,
    labelSmall = BaseFont(FontDefinition.Medium).Title.Small,
)

@Immutable
internal class AppTypography(
    val Regular: BaseFont = BaseFont(FontDefinition.Regular),
    val RegularItalic: BaseFont = BaseFont(FontDefinition.RegularItalic),
    val Medium: BaseFont = BaseFont(FontDefinition.Medium),
    val MediumItalic: BaseFont = BaseFont(FontDefinition.MediumItalic),
    val SemiBold: BaseFont = BaseFont(FontDefinition.SemiBold),
    val SemiBoldItalic: BaseFont = BaseFont(FontDefinition.SemiBoldItalic),
    val Bold: BaseFont = BaseFont(FontDefinition.Bold),
    val BoldItalic: BaseFont = BaseFont(FontDefinition.BoldItalic),
    val ExtraBold: BaseFont = BaseFont(FontDefinition.ExtraBold),
    val ExtraBoldItalic: BaseFont = BaseFont(FontDefinition.ExtraBoldItalic),
)


