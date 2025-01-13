package com.example.demo_structure.theme

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import com.example.demo_structure.R

/**
 * Created by Phạm Sơn at 15:49/23/11/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun IconImage(
    imageVector: Int,
    contentDescription: String?,
    color: Color = Color.Unspecified // Default to unspecified color
) {
    Icon(
        imageVector = ImageVector.vectorResource(imageVector),
        contentDescription = contentDescription,
        tint = color // Apply the custom color
    )
}




object AppIcons {
    val IconHomeSelect = R.drawable.ic_home_select
    val IconHomeUnSelect = R.drawable.ic_home_unselect
    val IconSearchSelect = R.drawable.ic_search_select
    val IconSearchUnSelect = R.drawable.ic_search_unselect
    val IconUserSelect = R.drawable.ic_user_select
    val IconUserUnSelect = R.drawable.ic_user_unselect
    val IconHeart = R.drawable.ic_heart
    val IconDot = R.drawable.ic_dot


}

@Composable
fun Int.toIcon(){
    IconImage(imageVector = this, contentDescription = null)
}