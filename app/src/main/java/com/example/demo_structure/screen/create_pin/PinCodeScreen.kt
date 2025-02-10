package com.example.demo_structure.screen.create_pin

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.R
import com.example.demo_structure.app.manager.theme.ApplicationTheme
import com.example.demo_structure.core.component.AppBarIcon
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.otp.PassCodeTextField
import com.example.demo_structure.screen.home.LoadingState
import com.example.demo_structure.screen.otp.OTPType
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
private fun PinCodeScreenPreview() {
    PinCodeScreenContent(
        modifier = Modifier,
        label = "Tạo mã pin mới",
        passCode = "",
        confirmPasscode = "",
        otpError = "",
        maxLength = 6,
        isLoading = false,
        onPassCodeChange = {

        },
        onConfirmPassCodeChange = {

        },
        onResetError = {

        },
        onNextStep = {

        },
        onComplete = {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinCodeScreen(
    modifier: Modifier = Modifier,
    viewModel: PinCodeViewModel = koinViewModel(),
    arguments: PinArguments? = null,
    onNavigateHomeScreen: () -> Unit,
    origin: String
) {
    val context = LocalContext.current
    val registerState by viewModel.registerUiState.collectAsStateWithLifecycle()
    val updatePasswordState by viewModel.updatePassWordUiState.collectAsStateWithLifecycle()
    val loginState by viewModel.loginUiState.collectAsStateWithLifecycle()

    var isLoading by remember { mutableStateOf(false) }
    val rememberHostState = remember { SnackbarHostState() }
    var otpError by remember { mutableStateOf("") }
    var isCompleteStep1 by remember { mutableStateOf(false) }

    val maxLength = 6

    fun verifyPassCode(arguments: PinArguments) {
        if (viewModel.passCode != viewModel.confirmPasscode) {
            otpError = "sai pass code"
        } else {
            if (arguments.type == OTPType.REGISTER.type) {
                viewModel.register(
                    arguments.email ?: "",
                    viewModel.passCode,
                    arguments.secret ?: ""
                )
            } else {
                viewModel.updatePassword(
                    arguments.email ?: "",
                    viewModel.passCode,
                    arguments.secret ?: ""
                )
            }
        }
    }

    fun autoLogin(email: String, passCode: String) {
        viewModel.login(email, passCode)
    }

    fun loginSuccess() {
        onNavigateHomeScreen.invoke()
    }

    LaunchedEffect(key1 = registerState) {
        when (val state = registerState) {
            is PinCodeState.Loading -> {
                isLoading = state.isLoading
            }

            is PinCodeState.RegisterSuccess -> {
                isLoading = false
                delay(200)
                if (state.isSuccess) {
                    autoLogin(state.email, state.passCode)
                }
            }

            is PinCodeState.Error -> {
                Toast.makeText(context, state.msg, Toast.LENGTH_SHORT).show()
                isLoading = false
            }

            else -> Unit
        }
    }

    LaunchedEffect(key1 = updatePasswordState) {
        when (val state = registerState) {
            is PinCodeState.Loading -> {
                isLoading = state.isLoading
            }

            is PinCodeState.UpdatePasswordSuccess -> {
                isLoading = false
            }

            is PinCodeState.Error -> {
                Toast.makeText(context, state.msg, Toast.LENGTH_SHORT).show()
                isLoading = false
            }

            else -> Unit
        }
    }

    LaunchedEffect(key1 = loginState) {
        when (val state = loginState) {
            is PinCodeState.Loading -> {
                isLoading = state.isLoading
            }

            is PinCodeState.LoginSuccess -> {
                isLoading = false
                viewModel.saveAuth(state.authentication)
            }

            is PinCodeState.Error -> {
                Toast.makeText(context, state.msg, Toast.LENGTH_SHORT).show()
                isLoading = false
                delay(500)
                loginSuccess()
            }

            else -> Unit
        }
    }


    ApplicationTheme {
        AppScaffold(
            modifier = modifier,
            snackBarHostState = rememberHostState
        ) {
            Column {
                TopAppBar(title = {
                    Text("")
                },
                    navigationIcon = {
                        AppBarIcon(
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            imageResource = R.drawable.ic_back_arrow
                        )
                    })
                PinCodeScreenContent(
                    modifier = modifier,
                    label = if (!isCompleteStep1) "Tạo mã pin mới" else "Nhập lại mã pin",
                    passCode = viewModel.passCode,
                    confirmPasscode = viewModel.confirmPasscode,
                    otpError = otpError,
                    maxLength = maxLength,
                    isLoading = isLoading,
                    onPassCodeChange = {
                        viewModel.passCode = it
                    },
                    onConfirmPassCodeChange = {
                        viewModel.confirmPasscode = it
                    },
                    onResetError = {
                        otpError = it
                    },
                    onNextStep = {
                        isCompleteStep1 = true
                    },
                    onComplete = {
                        arguments?.let {
                            verifyPassCode(it)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun PinCodeScreenContent(
    modifier: Modifier = Modifier,
    label: String,
    passCode: String,
    confirmPasscode: String,
    otpError: String,
    maxLength: Int,
    isLoading: Boolean,
    onPassCodeChange: (String) -> Unit,
    onConfirmPassCodeChange: (String) -> Unit,
    onResetError: (String) -> Unit,
    onNextStep: () -> Unit,
    onComplete: () -> Unit
) {
    var isEnableButton by remember { mutableStateOf(false) }
    val buttonContent = if (passCode.length == maxLength) "Hoàn tất" else "Tiếp tục"
    val isCompleteStep1 = passCode.length == maxLength
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.royal_blue),
        contentColor = Color.White,
        disabledContainerColor = colorResource(id = R.color.jumbo),
        disabledContentColor = colorResource(id = R.color.tuna)
    )
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
            .navigationBarsPadding()
            .imePadding()
    ) {
        val (textViewLabel, pinCode, pinCode2, columnBottom, loading) = createRefs()

        Text(modifier = Modifier
            .constrainAs(textViewLabel) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(bottom = 8.dp),
            style = TextStyle(fontSize = 24.sp),
            text = label,
            textAlign = TextAlign.Center)
        if (!isCompleteStep1) {
            PassCodeTextField(
                modifier = Modifier
                    .constrainAs(pinCode) {
                        top.linkTo(textViewLabel.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onValueChange = {
                    isEnableButton = it.length == maxLength && !isLoading
                    onPassCodeChange(it)
                },
                numDigits = maxLength,
                errorMessage = otpError
            )
        } else {
            PassCodeTextField(
                modifier = Modifier
                    .constrainAs(pinCode2) {
                        top.linkTo(textViewLabel.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onValueChange = {
                    isEnableButton = it.length == maxLength && !isLoading
                    onResetError("")
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
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = buttonColors,
            enabled = isEnableButton
        ) {
            Text(text = buttonContent)
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