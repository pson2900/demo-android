package com.example.demo_structure.app.manager.theme

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import com.example.demo_structure.R

/**
 * Created by Phạm Sơn at 15:49/23/11/24
 * Copyright (c) 2024 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun ToImage(
    modifier: Modifier = Modifier,
    imageResource: Int,
    color: Color? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    if (color != null) {
        Image(
            modifier = modifier.testTag("ToImage:$imageResource"),
            imageVector = ImageVector.vectorResource(imageResource),
            contentDescription = imageResource.toString(),
            contentScale = contentScale,
            colorFilter = ColorFilter.tint(color = color, BlendMode.SrcIn),
        )
    } else {
        Image(
            modifier = modifier.testTag("ToImage:$imageResource"),
            imageVector = ImageVector.vectorResource(imageResource),
            contentDescription = imageResource.toString(),
            contentScale = contentScale,
        )
    }

}


object AppIcons {
    // Navigation Icons
    val homeSelect = R.drawable.ic_home_select
    val homeUnselect = R.drawable.ic_home_unselect
    val educationSelect = R.drawable.ic_education_select
    val educationUnselect = R.drawable.ic_education_unselect
    val opportunitySelect = R.drawable.ic_opportunity_select
    val opportunityUnselect = R.drawable.ic_opportunity_unselect
    val communitySelect = R.drawable.ic_community_select
    val communityUnselect = R.drawable.ic_community_unselect
    val userSelect = R.drawable.ic_user_select
    val userUnselect = R.drawable.ic_user_unselect

    // Profile Icons
    val advancementIcon = R.drawable.ic_my_profile_advancement
    val arrowRightIcon = R.drawable.ic_arrow_right
    val addIcon = R.drawable.ic_add
    val shoppingForSportsEquipmentIcon = R.drawable.ic_shopping_for_sports_equipment
    val attachmentIcon = R.drawable.ic_attachment
    val basicInformationIcon = R.drawable.ic_basic_information
    val experienceIcon = R.drawable.ic_experience
    val educationIcon = R.drawable.ic_education
    val certificationIcon = R.drawable.ic_certification
    val cvMarkIcon = R.drawable.ic_cv_mark
    val languageIcon = R.drawable.ic_language
    val linkIcon = R.drawable.ic_link
    val referenceIcon = R.drawable.ic_reference
    val hobbyIcon = R.drawable.ic_hobby

    val arrowLeftIcon = R.drawable.ic_arrow_left
    val searchIcon = R.drawable.ic_search
    val avatarLogo = R.drawable.company_logo
    val OpportunityHeart = R.drawable.ic_my_profile_opprotunities_heart
    val OpportunityCrow = R.drawable.ic_my_profile_opprotunities_crow
    val OpportunityBag = R.drawable.ic_my_profile_opprotunities_bag

}

@Composable
fun Int.Generate(modifier: Modifier = Modifier, color: Color? = null) {
    ToImage(modifier = modifier, imageResource = this, color = color)
}