package com.example.demo_structure.core.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme

/**
 * Created by Phạm Sơn at 11:11/4/1/25
 * Copyright (c) 2025 Navigos Group. All rights reserved.
 * Email: son.pham@navigosgroup.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    navigationIcon: @Composable () -> Unit = {},
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarColors(
            containerColor = ProductXTheme.colorScheme.surface,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = ProductXTheme.colorScheme.onSurface,
            titleContentColor = ProductXTheme.colorScheme.onSurface,
            actionIconContentColor = ProductXTheme.colorScheme.onSurface
        ),
        modifier = modifier,
        navigationIcon = navigationIcon,
        title = title,
        actions = actions,
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun AppSearchBar(){
    TopAppBar {  }
}

@Composable
fun AppBarIcon(
    modifier: Modifier = Modifier,
    contentDescription: String?,
    imageResource: Int,
    clickable: ()-> Unit
) {
    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }
    Box(modifier = modifier.then(semantics)) {
        Icon(
            modifier = modifier.clickable {
                clickable.invoke()
            },
            painter = painterResource(imageResource),
            contentDescription = null,
            tint = Color.Black,
        )
    }
}

@Composable
fun TopSearchAppBar(
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit,
    content: String,
) {
    Box(
        modifier = modifier
            .paint(
                painterResource(id = R.drawable.vnw_gradient_status_bar),
                contentScale = ContentScale.FillBounds
            )
            .statusBarsPadding()
    ) {
        Row(
            modifier = modifier
                .align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier
                    .weight(7.5f)
                    .background(
                        color = Color.White, // Background color
                        shape = RoundedCornerShape(8.dp) // Rounded corners
                    )
                    .padding(10.dp)
                    .clickable {
                        // Hành động khi Text được nhấn
                        Log.d("QQQ", "Text Content clicked!")
                    },
                text = content,
                color = Color.Black, textAlign = TextAlign.Start

            )
            Text(
                modifier = Modifier
                    .weight(2.5f)
                    .padding(10.dp)
                    .clickable {
                        // Hành động khi Text được nhấn
                        Log.d("QQQ", "Text Cancel clicked!")
                    },

                text = "Cancel",

                color = Color.White, textAlign = TextAlign.Center
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun AppBarPreviewDark() {
    AppPreviewWrapper {
        /*Column {
            AppTopBar(title = { Text("Preview!") },
                navigationIcon = {
                    AppBarIcon(
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        imageResource = R.drawable.ic_arrow_left,
                        clickable = {

                        }
                    )
                })
            TopSearchAppBar(
                modifier = Modifier.fillMaxWidth(),
                actions = {},
                content = "Hello World Dark"
            )

        }
        */
        AppScaffold(backgroundColor = Color.White,
            topBar = {
                AppSearchBar()
            }) { }

    }
}
