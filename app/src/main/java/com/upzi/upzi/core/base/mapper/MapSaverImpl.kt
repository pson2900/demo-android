package com.upzi.myapplication.data.mapper

import androidx.compose.runtime.saveable.mapSaver

/**
 * Created by Phạm Sơn at 13:55/4/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
typealias DictionaryType = HashMap<String, Any?>

interface MapSaverImpl{
    fun toMap(): MutableMap<String, Any?>

    companion object {
        private fun <T : MapSaverImpl> fromMap(map: MutableMap<String, Any?>, factory: (MutableMap<String, Any?>) -> T): T {
            return factory(map)
        }

        /// example:  MapImpl.generate(User::parseMap)
        fun <T : MapSaverImpl> generate(
            factory: ( MutableMap<String, Any?>) -> T
        ) = mapSaver(
            save = { item -> item.toMap() },
            restore = { dictionaryType ->
                val map = dictionaryType.toMutableMap()
                fromMap(map, factory)
            }
        )
    }
}