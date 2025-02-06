package com.example.demo_structure.screen.user

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.base.UiStateWrapper
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.screen.user.component.BasicInformationItem
import com.example.demo_structure.screen.user.component.HeaderSection
import com.example.demo_structure.screen.user.component.OpportunitiesSection
import com.example.demo_structure.screen.user.component.ProfileStatusSection
import com.example.demo_structure.screen.user.component.SkillSection
import com.example.domain.model.MyProfile
import com.example.domain.model.Profile

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@Composable
internal fun UserScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToProfile: (Profile) -> Unit,
    clearUndoState: () -> Unit = {},
    userViewModel: UserViewModel
) {
    val myProfileState by userViewModel.myProfileState.collectAsStateWithLifecycle()
    val featureItemState by userViewModel.featureItemState.collectAsStateWithLifecycle()

    LaunchedEffect(userViewModel) {
        userViewModel.fetchMyProfile()
        userViewModel.fetchListItem()
    }

    DisposableEffect(userViewModel) {
        onDispose {
            clearUndoState()
        }
    }

    UiStateWrapper(
        uiState = myProfileState,
        onSuccessContent = {
            UserContent(
                modifier = Modifier.fillMaxSize(),
                onNavigateToProfile = onNavigateToProfile,
                onNavigateToLogin = onNavigateToLogin,
                myProfile = it
            )
        })
}


@Composable
internal fun UserContent(modifier: Modifier = Modifier, onNavigateToProfile: (Profile) -> Unit, onNavigateToLogin: () -> Unit, myProfile: MyProfile) {
    val rememberHostState = remember { SnackbarHostState() }
    ApplicationTheme {
        AppScaffold(
            modifier = modifier.fillMaxSize(),
            snackBarHostState = rememberHostState,
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> AppSnackBar(snackbarData) }
                )
            }
        ) { paddingValue ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(Modifier) {
                    myProfileBody(myProfile, onNavigateToProfile)
                }
            }
        }
    }
}

internal fun LazyListScope.myProfileBody(myProfile: MyProfile, onNavigateToProfile: (Profile) -> Unit ) {
    val basicInformation = (myProfile.profiles.find { it is Profile.BasicProfile } as? Profile.BasicProfile)?.basic
    item { HeaderSection(title = "${basicInformation?.lastName} ${basicInformation?.firstName}", avatar = basicInformation?.photo ?: "") }
    item { Spacer(Modifier.size(24.dp)) }
    item {
        ProfileStatusSection(onClick = {

        })
    }
    item { Spacer(Modifier.height(24.dp)) }
    item { OpportunitiesSection() }
    item { Spacer(Modifier.height(24.dp)) }
    item { SkillSection(myProfile.skill.map { it.name ?: "" }) }
    item { Spacer(Modifier.height(24.dp)) }
    item {
        AppText(
            text = "Thông tin hồ sơ", color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            style = ProductXTheme.typography.SemiBoldTitleLarge
        )
    }
    item { Spacer(Modifier.height(12.dp)) }
    items(myProfile.profiles.size) { index ->
        BasicInformationItem(myProfile.profiles[index], onNavigateToProfile)
    }
    item { Spacer(Modifier.height(12.dp)) }

}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserContentPreview() {
    AppPreviewWrapper { modifier ->
    }
}