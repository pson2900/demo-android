package com.upzi.upzi.screen.user

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.upzi.upzi.app.manager.theme.ProductXTheme
import com.upzi.upzi.core.base.DisplayUiStateContent
import com.upzi.upzi.core.component.AppPreviewWrapper
import com.upzi.upzi.core.component.AppScaffold
import com.upzi.upzi.core.component.AppSnackBar
import com.upzi.upzi.core.component.AppText
import com.upzi.upzi.core.component.ThemePreviews
import com.upzi.upzi.myProfileData
import com.upzi.upzi.screen.user.component.BasicInformationItem
import com.upzi.upzi.screen.user.component.HeaderSection
import com.upzi.upzi.screen.user.component.OpportunitiesSection
import com.upzi.upzi.screen.user.component.ProfileStatusSection
import com.upzi.upzi.screen.user.component.SkillSection
import com.upzi.domain.ifNotEmpty
import com.upzi.domain.ifNotNull
import com.upzi.domain.model.Basic
import com.upzi.domain.model.MyProfile
import com.upzi.domain.model.Profile

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@Composable
internal fun UserScreen(
    onNavigateToLogin: (String) -> Unit,
    onNavigateToProfile: (Profile) -> Unit,
    clearUndoState: () -> Unit = {},
    userViewModel: UserViewModel
) {
    val myProfileState by userViewModel.myProfileState.collectAsStateWithLifecycle()
    val featureItemState by userViewModel.featureItemState.collectAsStateWithLifecycle()

    val rememberHostState = remember { SnackbarHostState() }

    LaunchedEffect(userViewModel) {
        userViewModel.fetchMyProfile()
        userViewModel.fetchListItem()
        userViewModel.getAuth()
    }

    DisposableEffect(userViewModel) {
        onDispose {
            clearUndoState()
        }
    }
    AppScaffold(
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = rememberHostState,
        backgroundColor = ProductXTheme.colorScheme.background_2,
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            DisplayUiStateContent(
                uiState = myProfileState,
                onSuccessContent = { myProfile ->
                    MyProfileContent(
                        myProfile = myProfile,
                        onNavigateToProfile = onNavigateToProfile,
                    )
                })
        }
    }
}


@Composable
internal fun MyProfileContent(myProfile: MyProfile, onNavigateToProfile: (Profile) -> Unit) {
    val basicInformation = remember(myProfile.profiles) {
        (myProfile.profiles.find { it is Profile.BasicProfile } as? Profile.BasicProfile)?.basic
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        myProfileBody(myProfile, basicInformation, onNavigateToProfile)
    }
}

internal fun LazyListScope.myProfileBody(
    myProfile: MyProfile,
    basicInformation: Basic?,
    onNavigateToProfile: (Profile) -> Unit
) {
    basicInformation.ifNotNull {
        item {
            HeaderSection(
                title = "${it.lastName} ${it.firstName}",
                avatar = it.photo ?: ""
            )
        }
        item { Spacer(Modifier.size(24.dp)) }
    }

    item {
        ProfileStatusSection(onClick = {

        })
    }

    item { Spacer(Modifier.height(24.dp)) }
    item { OpportunitiesSection() }
    item { Spacer(Modifier.height(24.dp)) }

    myProfile.skill.ifNotEmpty {
        item { SkillSection(it.map { it.name ?: "" }) }
        item { Spacer(Modifier.height(24.dp)) }
    }

    myProfile.profiles.ifNotEmpty {
        Log.d("QQQ", "Size: ${it.size}")
        item {
            AppText(
                text = "Thông tin hồ sơ", color = Color.Black,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                style = ProductXTheme.typography.SemiBold.Title.Large
            )
        }
        item { Spacer(Modifier.height(12.dp)) }
        items(it.size) { index ->
            BasicInformationItem(it[index], onNavigateToProfile)
        }
        item { Spacer(Modifier.height(12.dp)) }
    }


}

@ThemePreviews
@Composable
fun UserContentPreview() {
    AppPreviewWrapper { modifier ->
        val onNavigateToProfile = { profile: Profile -> }
        MyProfileContent(
            myProfile = myProfileData.toDomain(),
            onNavigateToProfile = onNavigateToProfile,
        )
    }
}
