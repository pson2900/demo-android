package com.example.demo_structure.screen.user.component

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.demo_structure.core.component.ProductXPreviewWrapper

/**
 * Created by Phạm Sơn at 13:20/14/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
fun OpportunitiesSection(modifier: Modifier = Modifier){

}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OpportunitiesSectionPreview(){
    ProductXPreviewWrapper {
        OpportunitiesSection(it)
    }
}