package com.example.demo_structure.app.manager.theme

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import com.example.demo_structure.R

/**
 * Created by Phạm Sơn at 15:49/23/11/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun IconImage(
    modifier: Modifier = Modifier,
    imageResource: Int,
    contentDescription: String?,
    color: Color = Color.Transparent,
    contentScale: ContentScale = ContentScale.Fit
) {
    /*val tintColorFilter = if (color != Color.Unspecified) ColorFilter.tint(color) else null
    if (imageResource != 0) {
        val vectorPainter = remember(imageResource) {
            mutableStateOf<androidx.compose.ui.graphics.painter.Painter?>(null)
        }.value
        vectorPainter ?: try {
            val vector = ImageVector.vectorResource(id = imageResource)
            rememberVectorPainter(image = vector)
        } catch (e: Exception) {
            null
        }?.let {
            Image(
                modifier = modifier,
                painter = it,
                contentDescription = contentDescription,
                colorFilter = tintColorFilter,
                contentScale = contentScale
            )
        }
        ?: Image(
            modifier = modifier,
            painter = painterResource(id = imageResource),
            contentDescription = contentDescription,
            colorFilter = tintColorFilter,
            contentScale = contentScale
        )
    }*/
    if (color != null) {
//        colorFilter = ColorFilter.tint(color = color, BlendMode.SrcIn)
    } else {

    }
    Image(
        modifier = modifier,
        imageVector = ImageVector.vectorResource(imageResource),
        contentDescription = contentDescription,
//        colorFilter = ColorFilter.tint(color = color, BlendMode.SrcIn)

//        tint = color // Apply the custom color
    )
}


object AppIcons {
    val IconHomeSelect = R.drawable.ic_home_select
    val IconHomeUnSelect = R.drawable.ic_home_unselect
    val IconEducationSelect = R.drawable.ic_education_select
    val IconEducationUnSelect = R.drawable.ic_education_unselect
    val IconOpportunitySelect = R.drawable.ic_opportunity_select
    val IconOpportunityUnSelect = R.drawable.ic_opportunity_unselect
    val IconCommunitySelect = R.drawable.ic_community_select
    val IconCommunityUnSelect = R.drawable.ic_community_unselect
    val IconUserSelect = R.drawable.ic_user_select
    val IconUserUnSelect = R.drawable.ic_user_unselect
    val IconHeart = R.drawable.ic_heart
    val IconDot = R.drawable.ic_dot
    val IconAdvancement = R.drawable.ic_my_profile_advancement
    val IconArrowRight = R.drawable.ic_arrow_right
    val IconAdd = R.drawable.ic_add
    val IconShoppingForSportsEquipment = R.drawable.ic_shopping_for_sports_equipment


}

@Composable
fun Int.toIcon() {
    IconImage(imageResource = this, contentDescription = null)
}