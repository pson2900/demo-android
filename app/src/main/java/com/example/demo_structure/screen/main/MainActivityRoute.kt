package com.example.demo_structure.screen.main

import com.example.demo_structure.core.navigation.Destinations
import com.example.demo_structure.theme.AppIcons

/**
 * Created by Phạm Sơn at 15:56/2/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */

enum class MainDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val idItem: String,
    val title: String,
    val route: String,
) {
    HOME(
        selectedIcon = AppIcons.IconHomeSelect,
        unselectedIcon = AppIcons.IconHomeUnSelect,
        idItem = "HomeTag",
        title = "Trang chủ",
        route = Destinations.HOME_ROUTE
    ),
    EDUCATION(
        selectedIcon = AppIcons.IconEducationSelect,
        unselectedIcon = AppIcons.IconEducationUnSelect,
        idItem = "EducationTag",
        title = "Học tập",
        route = Destinations.EDUCATION_ROUTE
    ),
    OPPORTUNITY(
        selectedIcon = AppIcons.IconOpportunitySelect,
        unselectedIcon = AppIcons.IconOpportunityUnSelect,
        idItem = "OpportunityTag",
        title = "Cơ hội",
        route = Destinations.OPPORTUNITY_ROUTE
    ),

    COMMUNITY(
        selectedIcon = AppIcons.IconCommunitySelect,
        unselectedIcon = AppIcons.IconCommunityUnSelect,
        idItem = "CommunityTag",
        title = "Cộng đồng",
        route = Destinations.COMMUNITY_ROUTE
    ),

    USER(
        selectedIcon = AppIcons.IconUserSelect,
        unselectedIcon = AppIcons.IconUserUnSelect,
        idItem = "USER",
        title = "USER",
        route = Destinations.USER_ROUTE
    ),
}
