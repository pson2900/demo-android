package com.example.demo_structure.app.manager.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

/**
 * Created by Phạm Sơn at 15:13/4/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Immutable
class Shapes(
    val extraSmall: CornerBasedShape =  RoundedCornerShape(4.0.dp),
    val small: CornerBasedShape = RoundedCornerShape(4.0.dp),
    val medium: CornerBasedShape = RoundedCornerShape(4.0.dp),
    val large: CornerBasedShape = RoundedCornerShape(4.0.dp),
    val extraLarge: CornerBasedShape = RoundedCornerShape(4.0.dp),
)