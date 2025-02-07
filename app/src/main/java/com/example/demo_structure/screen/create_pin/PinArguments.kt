package com.example.demo_structure.screen.create_pin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PinArguments(
    val type: String? = null,
    val email: String? = null,
    val secret: String? = null
) : Parcelable