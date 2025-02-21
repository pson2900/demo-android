package com.upzi.domain.model

/**
 * Created by Phạm Sơn at 11:29/20/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
enum class FilterType(var title: String) {
    Sort("Sắp xếp theo"),
    Job("Công việc"),
    Salary("Mức lương"),
    Location("Địa điểm"),
    WorkType("Hình thức làm việc"),
    JobType("Loại công việc")
}

data class Filter(var type: FilterType, var isSelect: Boolean = false, val icon: Int) {
}