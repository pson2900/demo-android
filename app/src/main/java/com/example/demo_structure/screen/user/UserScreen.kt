package com.example.demo_structure.screen.user

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXApplicationTheme
import com.example.demo_structure.core.base.DataStateWrapper
import com.example.demo_structure.core.component.AppLoadingWheel
import com.example.demo_structure.core.component.ProductXPreviewWrapper
import com.example.demo_structure.core.component.ProductXScaffold
import com.example.demo_structure.core.component.ProductXSnackBar
import com.example.demo_structure.screen.user.component.BasicInformationItem
import com.example.demo_structure.screen.user.component.HeaderSection
import com.example.demo_structure.screen.user.component.OpportunitiesSection
import com.example.demo_structure.screen.user.component.ProfileStatusSection
import com.example.demo_structure.screen.user.component.SkillSection
import com.example.domain.model.BasicInformation
import com.example.domain.model.MyProfile
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the user's bookmarked articles. Includes support for loading and empty states.
 */
@Composable
internal fun UserScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    clearUndoState: () -> Unit = {},
    userViewModel: UserViewModel = koinViewModel()
) {
    val state by userViewModel.state.collectAsStateWithLifecycle()
    val rememberHostState = remember { SnackbarHostState() }
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        clearUndoState()
    }

    DataStateWrapper(modifier = modifier, state = state) { myProfile ->
        UserContent(
            modifier = modifier.fillMaxSize(),
            myProfile = myProfile,
            onNavigateToLogin = onNavigateToLogin,
            rememberHostState = rememberHostState
        )
    }
}

@Composable
fun UserContent(modifier: Modifier = Modifier, onNavigateToLogin: () -> Unit, rememberHostState: SnackbarHostState, myProfile: MyProfile) {
    ProductXApplicationTheme {
        ProductXScaffold(
            modifier = modifier,
            snackBarHostState = rememberHostState,
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> ProductXSnackBar(snackbarData) }
                )
            }
        ) { paddingValue ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.anti_flash_white))
            ) {
                val result = listOf(
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0),
                    BasicInformation(R.drawable.ic_my_profile_opprotunities_crow, "Kinh nghiệm làm việc", 0)
                )
                LazyColumn(Modifier) {
                    item { HeaderSection(title = "${myProfile.basic?.lastName + myProfile.basic?.firstName}", avatar = myProfile.basic?.photo ?: "") }
                    item { Spacer(Modifier.size(24.dp)) }
                    item {
                        ProfileStatusSection(onClick = {

                        })
                    }
                    item { Spacer(Modifier.height(24.dp)) }
                    item { OpportunitiesSection() }
                    item { Spacer(Modifier.height(24.dp)) }
                    item { SkillSection(myProfile.skill.map { it.skillName ?: "" }) }
                    item { Spacer(Modifier.height(24.dp)) }
                    item { Text("Thông tin hồ sơ", color = Color.Black, modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp), style = MaterialTheme.typography.titleLarge) }
                    item { Spacer(Modifier.height(12.dp)) }
                    items(result.size) {
                        BasicInformationItem(result[it])
                    }
                    item { Spacer(Modifier.height(12.dp)) }
                }
            }
        }
    }
}

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserContentPreview() {
    ProductXPreviewWrapper { modifier ->
        /* val hostState = remember { SnackbarHostState() }
         UserContent(onNavigateToLogin = {

         }, modifier = modifier, rememberHostState = hostState)*/
        LoadingState(modifier)
    }
}


@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        AppLoadingWheel(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize()
                .testTag("forYou:loading"),
            contentDesc = "forYou:loading",
        )
    }
}

