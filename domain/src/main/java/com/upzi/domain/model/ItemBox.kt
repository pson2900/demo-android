package com.upzi.domain.model

data class ItemBox(
    val id: Int,
    val name: String,
    val message: String,
    var isChecked: Boolean = false,
    var checkedAt: Long? = null
) {
}