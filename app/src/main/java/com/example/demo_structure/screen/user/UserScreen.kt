package com.example.demo_structure.screen.user

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
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.remote.network.RetrofitClient
import com.example.data.repository.MyProfileRepositoryImpl
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.base.DisplayUiStateContent
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppSnackBar
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.core.component.ThemePreviews
import com.example.demo_structure.screen.user.component.BasicInformationItem
import com.example.demo_structure.screen.user.component.HeaderSection
import com.example.demo_structure.screen.user.component.OpportunitiesSection
import com.example.demo_structure.screen.user.component.ProfileStatusSection
import com.example.demo_structure.screen.user.component.SkillSection
import com.example.domain.model.Basic
import com.example.domain.model.MyProfile
import com.example.domain.model.Profile
import com.example.domain.usecase.MyProfileUseCase

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
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            DisplayUiStateContent(
                uiState = myProfileState,
                onSuccessContent = {
                    MyProfileContent(
                        myProfile = it,
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
            style = ProductXTheme.typography.SemiBold.Title.Large
        )
    }
    item { Spacer(Modifier.height(12.dp)) }
    items(myProfile.profiles.size) { index ->
        BasicInformationItem(myProfile.profiles[index], onNavigateToProfile)
    }
    item { Spacer(Modifier.height(12.dp)) }

}

@ThemePreviews
@Composable
fun UserContentPreview() {
//    AppPreviewWrapper { modifier ->
//        UserScreen(
//            onNavigateToLogin = {},
//            onNavigateToProfile = { profile: Profile -> },
//            userViewModel = UserViewModel(
//                stateHandle = SavedStateHandle(),
//                myProfileUseCase = MyProfileUseCase( MyProfileRepositoryImpl(RetrofitClient.createService()))
//            ),
//            clearUndoState = {}
//        )
//    }

}
