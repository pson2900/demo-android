package com.upzi.upzi.screen.opportunity.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.upzi.upzi.app.manager.theme.AppIcons
import com.upzi.upzi.core.component.ThemePreviews

/**
 * Created by Phạm Sơn at 15:08/12/2/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun JobDetailSection() {
    Box(Modifier.background(color = Color.White)) {
        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(0.7f)) {
                    Text("Thực Tập Sinh UI/UX Designer")
                    Text("Grab")
                }

                Image(
                    modifier = Modifier.weight(0.3f).size(48.dp),
                    painter = painterResource(AppIcons.avatarLogo),
                    contentScale = ContentScale.Fit,
                    contentDescription = null)
            }
        }
        Divider()
        Column {

        }
    }
}

@Composable
@ThemePreviews
fun JobDetailSectionPreview() {
    JobDetailSection()
}