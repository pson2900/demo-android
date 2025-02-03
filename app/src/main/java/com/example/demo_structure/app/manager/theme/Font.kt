package com.example.demo_structure.app.manager.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.demo_structure.R

/**
 * Created by Phạm Sơn at 11:17/3/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
object AppFont {
    private enum class eDisplay(val size: TextUnit, val lineHeight: TextUnit, val weight: FontWeight) {
        X_Large(size = 96.sp, lineHeight = 112.sp, weight = FontWeight(700)),
        Large(size = 52.sp, lineHeight = 64.sp, weight = FontWeight(700)),
        Medium(size = 44.sp, lineHeight = 52.sp, weight = FontWeight(700)),
        Small(size = 36.sp, lineHeight = 44.sp, weight = FontWeight(700)),
    }

    private enum class eHeading(val size: TextUnit, val lineHeight: TextUnit, val weight: FontWeight) {
        XX_Large(size = 40.sp, lineHeight = 52.sp, weight = FontWeight(600)),
        X_Large(size = 36.sp, lineHeight = 44.sp, weight = FontWeight(600)),
        Large(size = 32.sp, lineHeight = 40.sp, weight = FontWeight(600)),
        Medium(size = 28.sp, lineHeight = 36.sp, weight = FontWeight(600)),
        Small(size = 24.sp, lineHeight = 32.sp, weight = FontWeight(600)),
        X_Small(size = 20.sp, lineHeight = 28.sp, weight = FontWeight(600)),
    }

    private enum class eTitle(val size: TextUnit, val lineHeight: TextUnit, val weight: FontWeight) {
        X_Large(size = 20.sp, lineHeight = 28.sp, weight = FontWeight(700)),
        Large(size = 18.sp, lineHeight = 28.sp, weight = FontWeight(700)),
        Medium(size = 16.sp, lineHeight = 24.sp, weight = FontWeight(700)),
        Small(size = 14.sp, lineHeight = 20.sp, weight = FontWeight(700)),
    }

    private enum class eLabel(val size: TextUnit, val lineHeight: TextUnit, val weight: FontWeight) {
        X_Large(size = 18.sp, lineHeight = 28.sp, weight = FontWeight(500)),
        Large(size = 16.sp, lineHeight = 24.sp, weight = FontWeight(500)),
        Medium(size = 14.sp, lineHeight = 20.sp, weight = FontWeight(500)),
        Small(size = 12.sp, lineHeight = 16.sp, weight = FontWeight(500)),
        X_Small(size = 10.sp, lineHeight = 14.sp, weight = FontWeight(500)),
    }

    private enum class eBody(val size: TextUnit, val lineHeight: TextUnit, val weight: FontWeight) {
        X_Large(size = 18.sp, lineHeight = 28.sp, weight = FontWeight(400)),
        Large(size = 16.sp, lineHeight = 24.sp, weight = FontWeight(400)),
        Medium(size = 14.sp, lineHeight = 20.sp, weight = FontWeight(400)),
        Small(size = 12.sp, lineHeight = 16.sp, weight = FontWeight(400)),
        X_Small(size = 10.sp, lineHeight = 14.sp, weight = FontWeight(400)),
    }

    object Regular {
        private val fontFamily: FontFamily = FontFamily(Font(R.font.nunito_regular))

        object Display {

            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.X_Large.size, lineHeight = eDisplay.X_Large.lineHeight, fontWeight = eDisplay.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Large.size, lineHeight = eDisplay.Large.lineHeight, fontWeight = eDisplay.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Medium.size, lineHeight = eDisplay.Medium.lineHeight, fontWeight = eDisplay.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Small.size, lineHeight = eDisplay.Small.lineHeight, fontWeight = eDisplay.Small.weight)

        }

        object Heading {

            val XX_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.XX_Large.size, lineHeight = eHeading.XX_Large.lineHeight, fontWeight = eHeading.XX_Large.weight)
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.X_Large.size, lineHeight = eHeading.X_Large.lineHeight, fontWeight = eHeading.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Large.size, lineHeight = eHeading.Large.lineHeight, fontWeight = eHeading.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Medium.size, lineHeight = eHeading.Medium.lineHeight, fontWeight = eHeading.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Small.size, lineHeight = eHeading.Small.lineHeight, fontWeight = eHeading.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.X_Small.size, lineHeight = eHeading.X_Small.lineHeight, fontWeight = eHeading.X_Small.weight)

        }

        object Title {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.X_Large.size, lineHeight = eTitle.X_Large.lineHeight, fontWeight = eTitle.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Large.size, lineHeight = eTitle.Large.lineHeight, fontWeight = eTitle.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Medium.size, lineHeight = eTitle.Medium.lineHeight, fontWeight = eTitle.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Small.size, lineHeight = eTitle.Small.lineHeight, fontWeight = eTitle.Small.weight)
        }

        object Label {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.X_Large.size, lineHeight = eLabel.X_Large.lineHeight, fontWeight = eLabel.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Large.size, lineHeight = eLabel.Large.lineHeight, fontWeight = eLabel.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Medium.size, lineHeight = eLabel.Medium.lineHeight, fontWeight = eLabel.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Small.size, lineHeight = eLabel.Small.lineHeight, fontWeight = eLabel.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.X_Small.size, lineHeight = eLabel.X_Small.lineHeight, fontWeight = eLabel.X_Small.weight)
        }

        object Body {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.X_Large.size, lineHeight = eBody.X_Large.lineHeight, fontWeight = eBody.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Large.size, lineHeight = eBody.Large.lineHeight, fontWeight = eBody.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Medium.size, lineHeight = eBody.Medium.lineHeight, fontWeight = eBody.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Small.size, lineHeight = eBody.Small.lineHeight, fontWeight = eBody.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.X_Small.size, lineHeight = eBody.X_Small.lineHeight, fontWeight = eBody.X_Small.weight)
        }
    }

    object Italic {
        private val fontFamily: FontFamily = FontFamily(Font(R.font.nunito_italic))

        object Display {

            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.X_Large.size, lineHeight = eDisplay.X_Large.lineHeight, fontWeight = eDisplay.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Large.size, lineHeight = eDisplay.Large.lineHeight, fontWeight = eDisplay.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Medium.size, lineHeight = eDisplay.Medium.lineHeight, fontWeight = eDisplay.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Small.size, lineHeight = eDisplay.Small.lineHeight, fontWeight = eDisplay.Small.weight)

        }

        object Heading {

            val XX_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.XX_Large.size, lineHeight = eHeading.XX_Large.lineHeight, fontWeight = eHeading.XX_Large.weight)
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.X_Large.size, lineHeight = eHeading.X_Large.lineHeight, fontWeight = eHeading.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Large.size, lineHeight = eHeading.Large.lineHeight, fontWeight = eHeading.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Medium.size, lineHeight = eHeading.Medium.lineHeight, fontWeight = eHeading.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Small.size, lineHeight = eHeading.Small.lineHeight, fontWeight = eHeading.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.X_Small.size, lineHeight = eHeading.X_Small.lineHeight, fontWeight = eHeading.X_Small.weight)

        }

        object Title {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.X_Large.size, lineHeight = eTitle.X_Large.lineHeight, fontWeight = eTitle.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Large.size, lineHeight = eTitle.Large.lineHeight, fontWeight = eTitle.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Medium.size, lineHeight = eTitle.Medium.lineHeight, fontWeight = eTitle.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Small.size, lineHeight = eTitle.Small.lineHeight, fontWeight = eTitle.Small.weight)
        }

        object Label {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.X_Large.size, lineHeight = eLabel.X_Large.lineHeight, fontWeight = eLabel.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Large.size, lineHeight = eLabel.Large.lineHeight, fontWeight = eLabel.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Medium.size, lineHeight = eLabel.Medium.lineHeight, fontWeight = eLabel.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Small.size, lineHeight = eLabel.Small.lineHeight, fontWeight = eLabel.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.X_Small.size, lineHeight = eLabel.X_Small.lineHeight, fontWeight = eLabel.X_Small.weight)
        }

        object Body {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.X_Large.size, lineHeight = eBody.X_Large.lineHeight, fontWeight = eBody.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Large.size, lineHeight = eBody.Large.lineHeight, fontWeight = eBody.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Medium.size, lineHeight = eBody.Medium.lineHeight, fontWeight = eBody.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Small.size, lineHeight = eBody.Small.lineHeight, fontWeight = eBody.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.X_Small.size, lineHeight = eBody.X_Small.lineHeight, fontWeight = eBody.X_Small.weight)
        }
    }

    object SemiBold {
        private val fontFamily: FontFamily = FontFamily(Font(R.font.nunito_semibold))

        object Display {

            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.X_Large.size, lineHeight = eDisplay.X_Large.lineHeight, fontWeight = eDisplay.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Large.size, lineHeight = eDisplay.Large.lineHeight, fontWeight = eDisplay.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Medium.size, lineHeight = eDisplay.Medium.lineHeight, fontWeight = eDisplay.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eDisplay.Small.size, lineHeight = eDisplay.Small.lineHeight, fontWeight = eDisplay.Small.weight)

        }

        object Heading {
            val XX_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.XX_Large.size, lineHeight = eHeading.XX_Large.lineHeight, fontWeight = eHeading.XX_Large.weight)
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.X_Large.size, lineHeight = eHeading.X_Large.lineHeight, fontWeight = eHeading.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Large.size, lineHeight = eHeading.Large.lineHeight, fontWeight = eHeading.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Medium.size, lineHeight = eHeading.Medium.lineHeight, fontWeight = eHeading.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.Small.size, lineHeight = eHeading.Small.lineHeight, fontWeight = eHeading.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eHeading.X_Small.size, lineHeight = eHeading.X_Small.lineHeight, fontWeight = eHeading.X_Small.weight)

        }

        object Title {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.X_Large.size, lineHeight = eTitle.X_Large.lineHeight, fontWeight = eTitle.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Large.size, lineHeight = eTitle.Large.lineHeight, fontWeight = eTitle.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Medium.size, lineHeight = eTitle.Medium.lineHeight, fontWeight = eTitle.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eTitle.Small.size, lineHeight = eTitle.Small.lineHeight, fontWeight = eTitle.Small.weight)
        }

        object Label {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.X_Large.size, lineHeight = eLabel.X_Large.lineHeight, fontWeight = eLabel.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Large.size, lineHeight = eLabel.Large.lineHeight, fontWeight = eLabel.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Medium.size, lineHeight = eLabel.Medium.lineHeight, fontWeight = eLabel.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.Small.size, lineHeight = eLabel.Small.lineHeight, fontWeight = eLabel.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eLabel.X_Small.size, lineHeight = eLabel.X_Small.lineHeight, fontWeight = eLabel.X_Small.weight)
        }

        object Body {
            val X_Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.X_Large.size, lineHeight = eBody.X_Large.lineHeight, fontWeight = eBody.X_Large.weight)
            val Large: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Large.size, lineHeight = eBody.Large.lineHeight, fontWeight = eBody.Large.weight)
            val Medium: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Medium.size, lineHeight = eBody.Medium.lineHeight, fontWeight = eBody.Medium.weight)
            val Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.Small.size, lineHeight = eBody.Small.lineHeight, fontWeight = eBody.Small.weight)
            val X_Small: TextStyle
                get() = TextStyle(fontFamily = fontFamily, fontSize = eBody.X_Small.size, lineHeight = eBody.X_Small.lineHeight, fontWeight = eBody.X_Small.weight)
        }
    }
}

@Immutable
class AppTypography(
    /* Regular */
    val RegularDisplayXLarge: TextStyle = AppFont.Regular.Display.X_Large,
    val RegularDisplayLarge: TextStyle = AppFont.Regular.Display.Large,
    val RegularDisplayMedium: TextStyle = AppFont.Regular.Display.Medium,
    val RegularDisplaySmall: TextStyle = AppFont.Regular.Display.Small,

    val RegularHeadingXXLarge: TextStyle = AppFont.Regular.Heading.XX_Large,
    val RegularHeadingXLarge: TextStyle = AppFont.Regular.Heading.X_Large,
    val RegularHeadingLarge: TextStyle = AppFont.Regular.Heading.Large,
    val RegularHeadingMedium: TextStyle = AppFont.Regular.Heading.Medium,
    val RegularHeadingSmall: TextStyle = AppFont.Regular.Heading.Small,
    val RegularHeadingXSmall: TextStyle = AppFont.Regular.Heading.X_Small,

    val RegularTitleXLarge: TextStyle = AppFont.Regular.Title.X_Large,
    val RegularTitleLarge: TextStyle = AppFont.Regular.Title.Large,
    val RegularTitleMedium: TextStyle = AppFont.Regular.Title.Medium,
    val RegularTitleSmall: TextStyle = AppFont.Regular.Title.Small,

    val RegularLabelXLarge: TextStyle = AppFont.Regular.Label.X_Large,
    val RegularLabelLarge: TextStyle = AppFont.Regular.Label.Large,
    val RegularLabelMedium: TextStyle = AppFont.Regular.Label.Medium,
    val RegularLabelSmall: TextStyle = AppFont.Regular.Label.Small,

    val RegularBodyXLarge: TextStyle = AppFont.Regular.Body.X_Large,
    val RegularBodyLarge: TextStyle = AppFont.Regular.Body.Large,
    val RegularBodyMedium: TextStyle = AppFont.Regular.Body.Medium,
    val RegularBodySmall: TextStyle = AppFont.Regular.Body.Small,

    /* Italic */
    val ItalicDisplayXLarge: TextStyle = AppFont.Italic.Display.X_Large,
    val ItalicDisplayLarge: TextStyle = AppFont.Italic.Display.Large,
    val ItalicDisplayMedium: TextStyle = AppFont.Italic.Display.Medium,
    val ItalicDisplaySmall: TextStyle = AppFont.Italic.Display.Small,

    val ItalicHeadingXXLarge: TextStyle = AppFont.Italic.Heading.XX_Large,
    val ItalicHeadingXLarge: TextStyle = AppFont.Italic.Heading.X_Large,
    val ItalicHeadingLarge: TextStyle = AppFont.Italic.Heading.Large,
    val ItalicHeadingMedium: TextStyle = AppFont.Italic.Heading.Medium,
    val ItalicHeadingSmall: TextStyle = AppFont.Italic.Heading.Small,
    val ItalicHeadingXSmall: TextStyle = AppFont.Italic.Heading.X_Small,

    val ItalicTitleXLarge: TextStyle = AppFont.Italic.Title.X_Large,
    val ItalicTitleLarge: TextStyle = AppFont.Italic.Title.Large,
    val ItalicTitleMedium: TextStyle = AppFont.Italic.Title.Medium,
    val ItalicTitleSmall: TextStyle = AppFont.Italic.Title.Small,

    val ItalicLabelXLarge: TextStyle = AppFont.Italic.Label.X_Large,
    val ItalicLabelLarge: TextStyle = AppFont.Italic.Label.Large,
    val ItalicLabelMedium: TextStyle = AppFont.Italic.Label.Medium,
    val ItalicLabelSmall: TextStyle = AppFont.Italic.Label.Small,

    val ItalicBodyXLarge: TextStyle = AppFont.Italic.Body.X_Large,
    val ItalicBodyLarge: TextStyle = AppFont.Italic.Body.Large,
    val ItalicBodyMedium: TextStyle = AppFont.Italic.Body.Medium,
    val ItalicBodySmall: TextStyle = AppFont.Italic.Body.Small,

    /* SemiBold */
    val SemiBoldDisplayXLarge: TextStyle = AppFont.SemiBold.Display.X_Large,
    val SemiBoldDisplayLarge: TextStyle = AppFont.SemiBold.Display.Large,
    val SemiBoldDisplayMedium: TextStyle = AppFont.SemiBold.Display.Medium,
    val SemiBoldDisplaySmall: TextStyle = AppFont.SemiBold.Display.Small,

    val SemiBoldHeadingXXLarge: TextStyle = AppFont.SemiBold.Heading.XX_Large,
    val SemiBoldHeadingXLarge: TextStyle = AppFont.SemiBold.Heading.X_Large,
    val SemiBoldHeadingLarge: TextStyle = AppFont.SemiBold.Heading.Large,
    val SemiBoldHeadingMedium: TextStyle = AppFont.SemiBold.Heading.Medium,
    val SemiBoldHeadingSmall: TextStyle = AppFont.SemiBold.Heading.Small,
    val SemiBoldHeadingXSmall: TextStyle = AppFont.SemiBold.Heading.X_Small,

    val SemiBoldTitleXLarge: TextStyle = AppFont.SemiBold.Title.X_Large,
    val SemiBoldTitleLarge: TextStyle = AppFont.SemiBold.Title.Large,
    val SemiBoldTitleMedium: TextStyle = AppFont.SemiBold.Title.Medium,
    val SemiBoldTitleSmall: TextStyle = AppFont.SemiBold.Title.Small,

    val SemiBoldLabelXLarge: TextStyle = AppFont.SemiBold.Label.X_Large,
    val SemiBoldLabelLarge: TextStyle = AppFont.SemiBold.Label.Large,
    val SemiBoldLabelMedium: TextStyle = AppFont.SemiBold.Label.Medium,
    val SemiBoldLabelSmall: TextStyle = AppFont.SemiBold.Label.Small,

    val SemiBoldBodyXLarge: TextStyle = AppFont.SemiBold.Body.X_Large,
    val SemiBoldBodyLarge: TextStyle = AppFont.SemiBold.Body.Large,
    val SemiBoldBodyMedium: TextStyle = AppFont.SemiBold.Body.Medium,
    val SemiBoldBodySmall: TextStyle = AppFont.SemiBold.Body.Small,
)

@Composable
fun a() {
    AppFont.Regular.Heading.XX_Large
}