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
    val iconTextId: String,
    val titleTextId: String,
    val route: String,
) {
    HOME(
        selectedIcon = AppIcons.IconHomeSelect,
        unselectedIcon = AppIcons.IconHomeUnSelect,
        iconTextId = "HOME",
        titleTextId = "HOME",
        route = Destinations.HOME_ROUTE
    ),

    SEARCH(
        selectedIcon = AppIcons.IconSearchSelect,
        unselectedIcon = AppIcons.IconSearchUnSelect,
        iconTextId = "SEARCH",
        titleTextId = "SEARCH",
        route = Destinations.EDUCATION_ROUTE
    ),

    USER(
        selectedIcon = AppIcons.IconUserSelect,
        unselectedIcon = AppIcons.IconUserUnSelect,
        iconTextId = "USER",
        titleTextId = "USER",
        route = Destinations.USER_ROUTE
    ),
}
