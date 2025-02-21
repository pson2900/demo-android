package com.upzi.upzi.core.base.mapper

import androidx.compose.runtime.saveable.listSaver

/**
 * Created by Phạm Sơn at 14:45/4/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
interface ListSaverImpl {
    fun toList(): List<Any?> // Chuyển đổi đối tượng thành danh sách

    companion object {
        private fun <T : ListSaverImpl> fromList(
            list: List<Any?>,
            factory: (List<Any?>) -> T
        ): T {
            return factory(list)
        }

        fun <T : ListSaverImpl> generate(factory: (List<Any?>) -> T) = listSaver(
            save = { it.toList() },
            restore = { fromList(it, factory) }
        )
    }
}