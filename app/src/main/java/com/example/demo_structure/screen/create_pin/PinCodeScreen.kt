package com.example.demo_structure.screen.create_pin

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.remote.UIState
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBarIcon
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.core.component.AppTopBar
import com.example.demo_structure.core.component.otp.PassCodeTextField
import com.example.demo_structure.screen.home.LoadingState
import com.example.demo_structure.screen.otp.OTPType
import com.example.demo_structure.util.extension.isJsonObjectRegex
import com.example.domain.model.Authentication
import com.example.domain.model.Register
import com.example.domain.model.UserProfile
import kotlinx.coroutines.delay
import org.json.JSONObject
import org.koin.androidx.compose.koinViewModel

@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PinCodeScreenPreview() {
    AppPreviewWrapper { modifier ->
        PinCodeScreenContent(
            modifier = modifier.fillMaxSize(),
            label = "Tạo mã pin mới",
            passCode = "",
            otpError = "",
            maxLength = 6,
            isLoading = false,
            isCompleteStep1 = false,
            onPassCodeChange = {

            },
            onConfirmPassCodeChange = {

            },
            onNextStep = {

            },
            onComplete = {

            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinCodeScreen(
    modifier: Modifier = Modifier,
    viewModel: PinCodeViewModel = koinViewModel(),
    arguments: PinArguments? = null,
    onNavigateHomeScreen: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val registerState by viewModel.registerUiState.collectAsStateWithLifecycle()
    val updatePasswordState by viewModel.updatePWUiState.collectAsStateWithLifecycle()
    val loginState by viewModel.loginUiState.collectAsStateWithLifecycle()

    val rememberHostState = remember { SnackbarHostState() }
    var isLoading by remember { mutableStateOf(false) }
    var otpError by remember { mutableStateOf("") }
    var isCompleteStep1 by remember { mutableStateOf(false) }
    val maxLength = 6
    viewModel.email = arguments?.email ?: ""

    fun onBackPressed() {
        if (!isCompleteStep1) {
            onBack.invoke()
        } else {
            isCompleteStep1 = false
            viewModel.passCode = ""
            viewModel.confirmPasscode = ""
        }
    }

    BackHandler(enabled = true) {
        onBackPressed()
    }

    ClearStateOnStop(viewModel = viewModel)

    LaunchedEffect(key1 = registerState) {
        handleRegisterState(
            state = registerState,
            isLoading = { isLoading = it },
            autoLogin = { viewModel.login() },
            onChangeError = {
                otpError = it
            }
        )
    }

    LaunchedEffect(key1 = updatePasswordState) {
        handleUpdatePasswordState(
            state = updatePasswordState,
            isLoading = { isLoading = it },
            autoLogin = { viewModel.login() },
            onChangeError = {
                otpError = it
            }
        )
    }

    LaunchedEffect(key1 = loginState) {
        handleLoginState(
            state = loginState,
            isLoading = { isLoading = it },
            saveAuth = {
                viewModel.saveAuth(it)
                viewModel.saveUserInfo(UserProfile())
            },
            onNavigateHomeScreen = onNavigateHomeScreen,
            onChangeError = {
                otpError = it
            }
        )
    }

    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                title = {
                    Text("")
                },
                navigationIcon = {
                    AppBarIcon(
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        imageResource = R.drawable.ic_back_black,
                        clickable = {
                            onBackPressed()
                        }
                    )
                })
        },
        snackBarHostState = rememberHostState,
        backgroundColor = ProductXTheme.colorScheme.background_1
    ) {
        PinCodeScreenContent(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            label = if (!isCompleteStep1) "Tạo mã pin mới" else "Nhập lại mã pin",
            passCode = viewModel.passCode,
            otpError = otpError,
            maxLength = maxLength,
            isLoading = isLoading,
            isCompleteStep1 = isCompleteStep1,
            onPassCodeChange = {
                viewModel.passCode = it
                otpError = ""
            },
            onConfirmPassCodeChange = {
                viewModel.confirmPasscode = it
                otpError = ""
            },
            onNextStep = {
                isCompleteStep1 = true
            },
            onComplete = {
                arguments?.let {
                    verifyPassCode(it, viewModel,
                        onChangeError = {
                            otpError = it
                        })
                }
            }
        )
    }
}

@Composable
private fun ClearStateOnStop(
    viewModel: PinCodeViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    // Safely update the current clearState function when it changes
    val currentClearState by rememberUpdatedState(viewModel::clearState)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                currentClearState()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

private fun verifyPassCode(
    arguments: PinArguments,
    viewModel: PinCodeViewModel,
    onChangeError: (String) -> Unit
) {
    if (viewModel.passCode != viewModel.confirmPasscode) {
        onChangeError("Mã nhập lại không đúng. Thử lại nhé!")
    } else {
        if (arguments.type == OTPType.REGISTER.type) {
            viewModel.register(arguments.secret ?: "")
        } else {
            viewModel.updatePassword(arguments.secret ?: "")
        }
    }
}

private suspend fun handleRegisterState(
    state: UIState<Register>,
    isLoading: (Boolean) -> Unit,
    autoLogin: () -> Unit,
    onChangeError: (String) -> Unit
) {
    when (val registerState = state) {
        is UIState.Loading -> isLoading(true)
        is UIState.Success -> {
            isLoading(false)
            delay(200)
            if (registerState.data.isSuccess) {
                autoLogin()
            }
        }

        is UIState.Error -> {
            onChangeError(handleError(state.appException.message))
            isLoading(false)
        }

        else -> Unit
    }
}

private fun handleUpdatePasswordState(
    state: UIState<Register>,
    isLoading: (Boolean) -> Unit,
    autoLogin: () -> Unit,
    onChangeError: (String) -> Unit
) {
    when (val updatePasswordState = state) {
        is UIState.Loading -> isLoading(true)
        is UIState.Success -> {
            isLoading(false)
            if (updatePasswordState.data.isSuccess) {
                autoLogin()
            }
        }

        is UIState.Error -> {
            onChangeError(handleError(state.appException.message))
            isLoading(false)
        }

        else -> Unit
    }
}

private suspend fun handleLoginState(
    state: UIState<Authentication>,
    isLoading: (Boolean) -> Unit,
    saveAuth: (Authentication) -> Unit,
    onNavigateHomeScreen: () -> Unit,
    onChangeError: (String) -> Unit
) {
    when (val loginState = state) {
        is UIState.Loading -> isLoading(true)
        is UIState.Success -> {
            isLoading(false)
            saveAuth(loginState.data)
            delay(200)
            onNavigateHomeScreen()
        }

        is UIState.Error -> {
            onChangeError(handleError(state.appException.message))
            isLoading(false)
        }

        else -> Unit
    }
}


fun handleError(error: String?): String{
    error?.let {
        if(isJsonObjectRegex(it)) {
            val json = JSONObject(it)
           return json.getString("message") ?: ""
        }else{
           return it
        }
    }
    return ""
}

@Composable
private fun PinCodeScreenContent(
    modifier: Modifier = Modifier,
    label: String,
    passCode: String,
    otpError: String,
    maxLength: Int,
    isLoading: Boolean,
    onPassCodeChange: (String) -> Unit,
    onConfirmPassCodeChange: (String) -> Unit,
    isCompleteStep1: Boolean,
    onNextStep: () -> Unit,
    onComplete: () -> Unit
) {
    val context = LocalContext.current
    var isEnableButton by remember { mutableStateOf(false) }
    val buttonContent = if (isCompleteStep1) "Hoàn tất" else "Tiếp tục"
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.royal_blue),
        contentColor = Color.White,
        disabledContainerColor = colorResource(id = R.color.jumbo),
        disabledContentColor = colorResource(id = R.color.tuna)
    )
    ConstraintLayout(
        modifier = modifier.padding(24.dp)
    ) {
        val (textViewLabel, pinCode, pinCode2, columnBottom, loading) = createRefs()

        AppText(modifier = Modifier
            .constrainAs(textViewLabel) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(bottom = 8.dp),
            style = ProductXTheme.typography.Regular.Heading.Small,
            color = colorResource(R.color.cod_gray),
            text = label,
            textAlign = TextAlign.Center)
        if (!isCompleteStep1) {
            PassCodeTextField(
                modifier = Modifier
                    .constrainAs(pinCode) {
                        top.linkTo(textViewLabel.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    } .testTag("TextFieldPassCode"),
                context = context,
                onValueChange = {
                    isEnableButton = it.length == maxLength && !isLoading
                    onPassCodeChange(it)
                },
                numDigits = maxLength,
                errorMessage = ""
            )
        } else {
            PassCodeTextField(
                modifier = Modifier
                    .constrainAs(pinCode2) {
                        top.linkTo(textViewLabel.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.testTag("TextFieldConfirmPassCode"),
                context = context,
                onValueChange = {
                    isEnableButton = it.length == maxLength && !isLoading
                    onConfirmPassCodeChange(it)
                },
                numDigits = maxLength,
                errorMessage = otpError
            )
        }

        Button(
            onClick = {
                if (!isCompleteStep1) {
                    onNextStep()
                    isEnableButton = false
                } else {
                    isEnableButton = false
                    onComplete()
                }
            },
            modifier = Modifier
                .constrainAs(columnBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(56.dp)
                .testTag("ButtonComplete"),
            shape = RoundedCornerShape(8.dp),
            colors = buttonColors,
            enabled = isEnableButton && !isLoading
        ) {
            Text(text = buttonContent, style = TextStyle(fontSize = 16.sp))
        }

        if (isLoading) {
            LoadingState(
                modifier = Modifier.constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}