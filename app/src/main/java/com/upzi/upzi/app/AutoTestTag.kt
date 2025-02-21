package com.upzi.upzi.app

import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.testTag

/**
 * Created by Phạm Sơn at 11:12/11/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
enum class ViewType {
    TEXT, BUTTON, IMAGE, OTHER
}

@Retention(AnnotationRetention.SOURCE) // Only keep in source code, not runtime
@Target(AnnotationTarget.FUNCTION) // Only applicable to functions
annotation class AutoTestTag

fun Modifier.autoTestTag(
    viewType: ViewType,  // Add a parameter for the view type
    tagPrefix: String? = null
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "autoTestTag"
        properties["viewType"] = viewType
        properties["tagPrefix"] = tagPrefix
    }
) {
    val currentComposableName = currentCompositeKeyHash.toString()
    val tag = buildString {
        if (!tagPrefix.isNullOrBlank()) {
            append(tagPrefix)
            append("_")
        }
        append(currentComposableName)
        append("_")  // Separator
        append(viewType.toString()) // Add view type
    }

    testTag(tag)
}