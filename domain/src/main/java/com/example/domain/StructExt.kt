package com.example.domain

/**
 * Created by Phạm Sơn at 15:44/12/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
inline fun <T> List<T>?.ifNotEmpty(defaultValue: (List<T>) -> Unit){
    if (this?.isNotEmpty() == true) defaultValue(this) else this
}

inline fun <T> List<T>?.ifEmpty(defaultValue: () -> Unit){
    if (this.isNullOrEmpty()) defaultValue() else this
}

inline fun <T> T?.ifNotNull(defaultValue: (T) -> Unit){
    if (this != null) defaultValue(this) else this
}


inline fun <T> T?.ifNull(defaultValue: () -> Unit){
    if (this == null) defaultValue() else this
}