package com.example.domain.model

data class ItemBox(val id: Int, val name: String, val message: String, var isChecked: Boolean?= false) {
}