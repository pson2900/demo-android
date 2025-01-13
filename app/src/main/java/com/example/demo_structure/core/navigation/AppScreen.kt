package com.example.myapplication.core.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by Phạm Sơn at 14:59/3/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@Composable
internal fun AppScreen(
    onTopicClick: (String) -> Unit,
    modifier: Modifier = Modifier,
//    viewModel: ForYouViewModel = hiltViewModel(),
) {
    Log.d("QQQ","onTopicClick: ${onTopicClick}")
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        Text("AppScreen: ${onTopicClick}")
    }
}