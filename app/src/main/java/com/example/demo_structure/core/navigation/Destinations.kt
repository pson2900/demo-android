package com.example.demo_structure.core.navigation

import android.util.Log
import com.example.demo_structure.app.manager.theme.AppIcons

/**
 * Created by Phạm Sơn at 10:10/23/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
abstract class DestinationItem(route: String) : Destinations(route) {
    abstract val selectedIcon: Int
    abstract val unselectedIcon: Int
    abstract val testTag: String
    abstract val title: String
}

sealed class Destinations(val route: String) {
    class Main : Destinations(route = "main") {


        companion object {
            val Tab = "tab"
            const val route: String = "main"
            fun createRoute(tabRoute: DestinationItem): String {
                Log.d("QQQ","createRoute tabRoute: ${tabRoute}")
                return route+"?${Tab}=${tabRoute.route}"
            }
            fun getEntries(): MutableList<DestinationItem> {
                return mutableListOf<DestinationItem>().apply {
                    add(Home)
                    add(Education)
                    add(Opportunity)
                    add(Community)
                    add(User)
                }
            }
        }

        object Home : DestinationItem(route = "home") {
            override val selectedIcon: Int = AppIcons.homeSelect
            override val unselectedIcon: Int = AppIcons.homeUnselect
            override val testTag: String = "HomeTag"
            override val title: String = "Trang chủ"

        }

        object Opportunity : DestinationItem(route = "opportunity") {
            override val selectedIcon: Int = AppIcons.opportunitySelect
            override val unselectedIcon: Int = AppIcons.opportunityUnselect
            override val testTag: String = "OpportunityTag"
            override val title: String = "Tuyển dụng"

        }

        object Education : DestinationItem(route = "education") {
            override val selectedIcon: Int = AppIcons.educationSelect
            override val unselectedIcon: Int = AppIcons.educationUnselect
            override val testTag: String = "EducationTag"
            override val title: String = "Học tập"

        }

        object Community : DestinationItem(route = "community") {
            override val selectedIcon: Int = AppIcons.communitySelect
            override val unselectedIcon: Int = AppIcons.communityUnselect
            override val testTag: String = "CommunityTag"
            override val title: String = "Cộng đồng"
        }

        object User : DestinationItem(route = "user") {
            override val selectedIcon: Int = AppIcons.userSelect
            override val unselectedIcon: Int = AppIcons.userUnselect
            override val testTag: String = "UserTag"
            override val title: String = "Cá nhân"

        }
    }

    object JobDetail : Destinations(route = "detail") {
        const val JOB_DETAIL_ID = "jobId"
        const val ORIGIN = "origin"
        fun createRoute(jobId: String, origin: String): String {
            return "detail/$jobId?origin=${origin}"
        }
    }

    object Login : Destinations(route = "login"){
        const val EMAIL = "email"
        fun createRoute(email: String): String {
            return "login/$email"
        }
    }
    object Email : Destinations(route = "email")

    object OTP : Destinations(route = "verify_otp") {
        const val ORIGIN = "origin"
        const val EMAIL = "email"
        fun createRoute(email: String, origin: String): String {
            return "verify_otp/$email?origin=${origin}"
        }
    }

    object CreatePin : Destinations(route = "create_pin") {
        const val JSON = "json"
        fun createRoute(pinJson: String): String {
            return "create_pin/$pinJson"
        }
    }
}