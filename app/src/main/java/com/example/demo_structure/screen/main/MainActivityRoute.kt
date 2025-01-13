package com.example.demo_structure.screen.main

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.example.demo_structure.screen.home.HomeRoute
import com.example.demo_structure.screen.search_result.SearchResultRoute
import com.example.demo_structure.screen.user.UserRoute
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
        route = "main/home",
    ),

    SEARCH(
        selectedIcon = AppIcons.IconSearchSelect,
        unselectedIcon = AppIcons.IconSearchUnSelect,
        iconTextId = "SEARCH",
        titleTextId = "SEARCH",
        route = "main/search"
    ),

    USER(
        selectedIcon = AppIcons.IconUserSelect,
        unselectedIcon = AppIcons.IconUserUnSelect,
        iconTextId = "USER",
        titleTextId = "USER",
        route = "main/user",
    ),
}

fun NavGraphBuilder.addHomeGraph(
    onSnackSelected: (Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    HomeRoute.toScreen(
        navGraphBuilder = this,
        modifier = modifier,
        onTopicClick = {

        },
        onSnackSelected = onSnackSelected,
    )
    SearchResultRoute.toScreen(
        navGraphBuilder = this,
        modifier = modifier,
        onTopicClick = {

        },
    )
    UserRoute.toScreen(
        navGraphBuilder = this,
        modifier = modifier,
        onTopicClick = {

        },
    )

}
