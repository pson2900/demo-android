package com.example.demo_structure.screen.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.remote.UIState
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppBarIcon
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppTopBar
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.core.component.CountdownTextView
import com.example.demo_structure.core.component.otp.OTPTextField
import com.example.demo_structure.core.component.otp.OtpTextFieldDefaults
import com.example.demo_structure.screen.create_pin.PinArguments
import com.example.demo_structure.screen.home.LoadingState
import com.example.demo_structure.util.extension.buildClickableText

@Preview(showBackground = true)
@Composable
private fun OTPScreenPreview() {
    var screenState by remember { mutableStateOf(OTPScreenState()) }
    AppPreviewWrapper { modifier ->
        OTPScreenContent(
            modifier = Modifier.fillMaxSize(),
            viewModel = null,
            email = "demo@gmail.com",
            type = "",
            screenState = screenState,
            onStateChange = { newScreenState ->
                screenState = newScreenState
            }
        )
    }
}

data class OTPScreenState(
    val sendOtpSuccess: Boolean = false,
    val isResend: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isValidOtp: Boolean = false,
    val secret: String = "",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyOTPScreen(
    modifier: Modifier = Modifier,
    viewModel: VerifyOTPViewModel,
    email: String,
    origin: String,
    onNavigatePinCode: (PinArguments) -> Unit,
    onBack: () -> Unit
) {
    var screenState by remember { mutableStateOf(OTPScreenState()) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val rememberHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        if (origin == OTPType.REGISTER.type) {
            viewModel.sendOtp(email)
        } else {
            viewModel.forgetPassword(email)
        }
    }


    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.clearOTPState()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    HandleOtpState(viewModel = viewModel,
        otpScreenState = screenState,
        onStateChange = { newState ->
            screenState = newState
            if (screenState.isValidOtp) {
                screenState = screenState.copy(isValidOtp = false)
                onNavigatePinCode(
                    PinArguments(
                        type = origin,
                        email = email,
                        secret = screenState.secret
                    )
                )
            }
        })

    AppScaffold(
        backgroundColor = ProductXTheme.colorScheme.background_1,
        modifier = Modifier,
        snackBarHostState = rememberHostState, topBar = {
            AppTopBar(modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                title = { Text("") },
                navigationIcon = {
                    AppBarIcon(
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        imageResource = R.drawable.ic_arrow,
                        clickable = {
                            onBack.invoke()
                        }
                    )
                })
        }
    ) {
        OTPScreenContent(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            viewModel = viewModel,
            email = email,
            type = origin,
            screenState = screenState,
            onStateChange = { newScreenState ->
                screenState = newScreenState
            }
        )
    }
}

@Composable
private fun OTPScreenContent(
    modifier: Modifier = Modifier,
    viewModel: VerifyOTPViewModel? = null,
    email: String,
    type: String,
    screenState: OTPScreenState,
    onStateChange: (OTPScreenState) -> Unit
) {
    val maxLength = 4
    var otp by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    ConstraintLayout(
        modifier = modifier.padding(24.dp)
    ) {
        val (tvTitle, tvEmail,tvDescription, otpTextField, textViewError, textViewMessage, columnBottom, loading) = createRefs()
        AppText(
            text = "Mã xác nhận đã được gửi qua email",
            modifier = Modifier.constrainAs(tvTitle) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth(),
            style = ProductXTheme.typography.Regular.Title.X_Large.copy(textAlign = TextAlign.Left),
            color = colorResource(R.color.cod_gray),
        )

        AppText(
            modifier = Modifier
                .constrainAs(tvEmail) {
                    top.linkTo(tvTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }.fillMaxWidth() .testTag("TextViewEmail"),
            maxLines = 2,
            style = ProductXTheme.typography.Regular.Title.X_Large.copy(textAlign = TextAlign.Left),
            color = colorResource(R.color.violets_are_blue),
            overflow = TextOverflow.Ellipsis,
            text = "$email"
        )

        AppText(
            modifier = Modifier
                .constrainAs(tvDescription) {
                    top.linkTo(tvEmail.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 8.dp),
            style = ProductXTheme.typography.Regular.Body.Large,
            color = colorResource(R.color.cod_gray),
            text = "Hãy kiểm tra hộp thư Chính hoặc Spam trong mail của bạn nhé"
        )

        fun verifyOtp(otp: String) {
            if (otp.length == maxLength) {
                viewModel?.verifyOtp(email, otp)
            }
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        OTPTextField(
            modifier = Modifier
                .width(260.dp)
                .constrainAs(otpTextField) {
                    top.linkTo(tvDescription.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 32.dp)
                .focusRequester(focusRequester)
                .testTag("OTPTextField"),
            value = otp,
            onTextChanged = {
                onStateChange(screenState.copy(errorMessage = ""))
                otp = it
                verifyOtp(it)
            },
            numDigits = maxLength, // Number of digits in OTP
            isMasked = false, // Mask digits for security
            digitContainerStyle = OtpTextFieldDefaults.outlinedContainer(size = 24.dp), // Choose style (outlined or underlined)
            textStyle = ProductXTheme.typography.SemiBold.Heading.Small, // Configure text style
            isError = screenState.errorMessage.isNotEmpty() // Indicate whether the OTP field is in an error state
        )

        if (screenState.errorMessage.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .constrainAs(textViewError) {
                        top.linkTo(otpTextField.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 24.dp),
                style = TextStyle(color = Color.Red, fontSize = 16.sp),
                text = screenState.errorMessage,
                textAlign = TextAlign.Center
            )
        }

        ColumnBottom(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(columnBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            screenState = screenState,
            email = email,
            type = type,
            onStateChange = onStateChange,
            onClearOtp = {
                onStateChange(screenState.copy(errorMessage = ""))
                otp = ""
            },
            viewModel = viewModel
        )

        if (screenState.isLoading) {
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

@Composable
private fun ColumnBottom(
    modifier: Modifier = Modifier,
    screenState: OTPScreenState,
    email: String,
    type: String,
    onStateChange: (OTPScreenState) -> Unit,
    onClearOtp: () -> Unit,
    viewModel: VerifyOTPViewModel?
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        if (screenState.sendOtpSuccess) {
            if (screenState.isResend) {
                ResendOtpText(viewModel, email, type, onStateChange = {
                    onStateChange(screenState.copy(sendOtpSuccess = false))
                    onClearOtp.invoke()
                })
            } else {
                CountdownResendOtpText {
                    onStateChange(screenState.copy(isResend = true))
                }
            }
        }
    }
}

@Composable
private fun ResendOtpText(
    viewModel: VerifyOTPViewModel?,
    email: String,
    type: String,
    onStateChange: (Boolean) -> Unit
) {
    val resendString = buildClickableText(
        text = "Chưa nhận được mã? Gửi lại",
        clickableText = "Gửi lại",
        tag = "tag_name",
        style = SpanStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),
        textLinkStyles = SpanStyle(
            color = colorResource(R.color.violets_are_blue),
            fontSize = 16.sp
        ),
        onClick = {
            onStateChange.invoke(true)
            if (type == OTPType.REGISTER.type) {
                viewModel?.sendOtp(email = email)
            } else {
                viewModel?.forgetPassword(email = email)
            }
        }
    )

    BasicText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .testTag("textViewResendOtp"),
        text = resendString,
        style = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp)
    )
}


@Composable
private fun CountdownResendOtpText(onCountdownFinished: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Chưa nhận được mã? ",
            style = TextStyle(
                color = colorResource(R.color.black),
                fontSize = 16.sp
            )
        )
        CountdownTextView(
            Modifier.wrapContentWidth()
                .testTag("textView Countdown"),
            value = "Gửi lại",
            style = TextStyle(
                color = colorResource(R.color.boulder),
                fontSize = 16.sp
            ),
            minutesState = (59 / 60f),
            onCountdownFinished = {
                onCountdownFinished.invoke()
            }
        )
    }
}

@Composable
fun HandleOtpState(
    viewModel: VerifyOTPViewModel,
    otpScreenState: OTPScreenState,
    onStateChange: (OTPScreenState) -> Unit
) {
    val otpState by viewModel.otpUiState.collectAsStateWithLifecycle()
    val verifyOtpState by viewModel.verifyOtpUiState.collectAsStateWithLifecycle()
    val pwState by viewModel.forgetPwUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = otpState) {
        when (val state = otpState) {
            is UIState.Loading -> onStateChange(otpScreenState.copy(isLoading = true))
            is UIState.Success -> {
                onStateChange(
                    otpScreenState.copy(
                        sendOtpSuccess = state.data.isSuccess,
                        isResend = false,
                        isLoading = false
                    )
                )
            }

            is UIState.Error -> onStateChange(
                otpScreenState.copy(
                    isLoading = false,
                    sendOtpSuccess = true,
                    isResend = true
                )
            )

            is UIState.Idle -> Unit
        }
    }

    LaunchedEffect(key1 = pwState) {
        when (val state = pwState) {
            is UIState.Loading -> onStateChange(otpScreenState.copy(isLoading = true))
            is UIState.Success -> onStateChange(
                otpScreenState.copy(
                    sendOtpSuccess = state.data.isSuccess,
                    isResend = false,
                    isLoading = false
                )
            )

            is UIState.Error -> onStateChange(
                otpScreenState.copy(
                    isLoading = false,
                    sendOtpSuccess = true,
                    isResend = true
                )
            )

            is UIState.Idle -> Unit
        }
    }

    LaunchedEffect(key1 = verifyOtpState) {
        when (val state = verifyOtpState) {
            is UIState.Loading -> onStateChange(otpScreenState.copy(isLoading = true))
            is UIState.Success ->{
                val errorMessage = if(state.data.isValid== false && state.data.secret.isNullOrEmpty()) state.data.message else ""
                onStateChange(
                    otpScreenState.copy(
                        isLoading = false,
                        isValidOtp = state.data.isValid,
                        errorMessage = errorMessage,
                        secret = state.data.secret
                    )
                )

            }
            is UIState.Error -> onStateChange(
                otpScreenState.copy(
                    isLoading = false,
                    sendOtpSuccess = true,
                    isResend = true
                )
            )

            is UIState.Idle -> Unit
        }
    }
}