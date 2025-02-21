package com.upzi.upzi.core.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.ApplicationTheme
import com.upzi.upzi.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 15:57/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
/**
 * The main background for the app.
 * Uses [LocalBackgroundTheme] to set the color and tonal elevation of a [Surface].
 *
 * @param modifier Modifier to be applied to the background.
 * @param content The background content.
 */


@Composable
fun AppBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    background: Color = Color.Unspecified,
    elevation: Dp = Dp.Unspecified
) {
    AppSurface(
        modifier = modifier.fillMaxSize(),
        backgroundColor = if (background == Color.Unspecified) ProductXTheme.colorScheme.background_1 else background,
        elevation = if (elevation == Dp.Unspecified) 0.dp else elevation,
        content = content
    )
}

/**
 * Multipreview annotation that represents light and dark themes. Add this annotation to a
 * composable to render the both themes.
 */
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
annotation class ThemePreviews

@ThemePreviews
@Composable
fun BackgroundDefault() {
    ApplicationTheme(dynamicColor = true) {
        AppBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun BackgroundDynamic() {
    ApplicationTheme(dynamicColor = false) {
        AppBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun BackgroundAndroid() {
    ApplicationTheme {
        AppBackground(Modifier.size(100.dp), content = {})
    }
}

