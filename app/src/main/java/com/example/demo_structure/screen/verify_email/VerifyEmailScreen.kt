package com.example.demo_structure.screen.verify_email

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.remote.UIState
import com.example.demo_structure.R
import com.example.demo_structure.app.autoTestTag
import com.example.demo_structure.app.manager.theme.ProductXTheme
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.AppScaffold
import com.example.demo_structure.core.component.AppText
import com.example.demo_structure.core.component.AppTextField
import com.example.demo_structure.core.component.WebViewWithClient
import com.example.demo_structure.screen.home.LoadingState
import com.example.demo_structure.screen.otp.OTPType
import com.example.demo_structure.util.extension.buildClickableText
import com.example.demo_structure.util.extension.findActivity
import com.example.demo_structure.util.extension.hideKeyboardAndClearFocus
import com.example.domain.model.VerifyEmail


@Preview("Light Mode")
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VerifyEmailPreview() {
    AppPreviewWrapper { modifier ->
        VerifyEmailContent(
            email = "demo@hihi.com",
            emailError = "",
            isEnableButton = true,
            isChecked = false,
            isSuccess = false,
            isLoading = false,
            onEmailChange = {},
            onCheckboxChange = {},
            onButtonClick = {},
            onLinkClick = {

            },
            onVerifyEmail = {}
        )
    }
}

@Composable
fun VerifyEmailScreen(
    modifier: Modifier = Modifier,
    viewModel: VerifyEmailViewModel,
    onNavigateToVerifyOtp: (String, String) -> Unit,
    onNavigateToLogin: (String) -> Unit
) {
    val context = LocalContext.current
    val emailState by viewModel.uiState.collectAsStateWithLifecycle()
    val rememberHostState = remember { SnackbarHostState() }

    var email by rememberSaveable { mutableStateOf(viewModel.email) }
    var isChecked by rememberSaveable { mutableStateOf(viewModel.isChecked) }

    var emailError by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val isEnableButton = email.isNotEmpty() && isChecked && emailError.isEmpty() && !isLoading

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel) {

    }

    val intent =
        remember {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.vietnamworks.com/quy-dinh-bao-mat?utm_source_navi=footer")
            )
        }


    fun getError(): String {
        return if (email.isNotEmpty() && !viewModel.authUseCase.isValidEmail(email)) "Vui lòng nhập địa chỉ email hợp lệ\n(ví dụ: username@domain.com)" else ""
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
        emailError = ""
        isSuccess = newEmail.isNotEmpty() && viewModel.authUseCase.isValidEmail(newEmail)
    }

    fun onCheckboxChange(newChecked: Boolean) {
        isChecked = newChecked
    }

    fun onButtonClick() {
        if (emailError.isEmpty() && email.isNotEmpty()) {
            viewModel.verifyEmail(email)
        }
    }

    fun onLinkClick() {
        context.startActivity(intent)
    }

    BackHandler(enabled = true) {
        context.findActivity()?.finish()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.clearEmailState()
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                viewModel.email = email
                viewModel.isChecked = isChecked
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(key1 = emailState) {
        when (val state = emailState) {
            is UIState.Loading -> {
                isLoading = true
            }

            is UIState.Success -> {
                isLoading = false
                if (state.data.found == true) {
                    onNavigateToLogin(email)
                } else {
                    onNavigateToVerifyOtp(email, OTPType.REGISTER.type)
                }
            }

            is UIState.Error -> {
                isLoading = false
                Toast.makeText(context, state.appException.message, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

    AppScaffold(
        modifier = modifier,
        snackBarHostState = rememberHostState,
        backgroundColor = ProductXTheme.colorScheme.background_1,
    ) {
        VerifyEmailContent(
            email = email,
            emailError = emailError,
            isEnableButton = isEnableButton,
            isChecked = isChecked,
            isSuccess = isSuccess,
            isLoading = isLoading,
            onEmailChange = ::onEmailChange,
            onCheckboxChange = ::onCheckboxChange,
            onButtonClick = ::onButtonClick,
            onLinkClick = ::onLinkClick,
            onVerifyEmail = {
                emailError = getError()
            }
        )
    }
}


@Composable
fun VerifyEmailContent(
    email: String,
    emailError: String,
    isEnableButton: Boolean,
    isChecked: Boolean,
    isSuccess: Boolean,
    isLoading: Boolean,
    onEmailChange: (String) -> Unit,
    onCheckboxChange: (Boolean) -> Unit,
    onButtonClick: () -> Unit,
    onLinkClick: () -> Unit,
    onVerifyEmail: () -> Unit,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    ConstraintLayout(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    hideKeyboardAndClearFocus(context, focusManager, keyboardController)
                })
            }
            .padding(top = 48.dp)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        val buttonColors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.royal_blue),
            contentColor = Color.White,
            disabledContainerColor = colorResource(id = R.color.jumbo),
            disabledContentColor = colorResource(id = R.color.tuna),
        )
        val color =
            if (isEnableButton) colorResource(R.color.white) else colorResource(R.color.tuna)
        val (logo, textview, emailTextField, columnBottom, loading) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_rondy_stickers),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 24.dp)
                .height(110.dp)
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .testTag("imageLogo")
        )
        AppText(
            text = "Khám phá con đường sự nghiệp của riêng bạn",
            modifier = Modifier
                .constrainAs(textview) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 24.dp, end = 24.dp),
            style = ProductXTheme.typography.SemiBold.Heading.Small,
            textAlign = TextAlign.Center
        )

        AppTextField(
            modifier = Modifier
                .padding(24.dp)
                .constrainAs(emailTextField) {
                    top.linkTo(textview.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .testTag("textFieldEmail"),
            hint = "Email của bạn",
            textValue = email,
            onValueChange = onEmailChange,
            onDone = {
                hideKeyboardAndClearFocus(context, focusManager, keyboardController)
            },
            onFocusChanged = {
                if (!it) {
                    onVerifyEmail.invoke()
                }
            },
            error = emailError,
            isSuccess = isSuccess
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(columnBottom) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(12.dp)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .testTag("Checkbox"),
                    checked = isChecked,
                    onCheckedChange = onCheckboxChange,
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = colorResource(R.color.violets_are_blue),
                        uncheckedColor = Color.Gray
                    )
                )
                val annotatedString = buildClickableText(
                    text = "Bằng việc click vào ô này, bạn đã đồng ý với Điều khoản dịch vụ và chính sách bảo mật của X",
                    clickableText = "Điều khoản dịch vụ và chính sách bảo mật",
                    tag = "tag_name",
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 17.sp
                    ),
                    textLinkStyles = SpanStyle(
                        color = colorResource(R.color.violets_are_blue),
                        fontSize = 17.sp
                    ),
                    onClick = {
                        onLinkClick.invoke()
                    })

                BasicText(
                    text = annotatedString,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .testTag("textViewPrivacyPolicy"),
                    style = ProductXTheme.typography.Regular.Title.Medium,
                )
            }

            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(56.dp)
                    .testTag("ButtonNext"),
                shape = RoundedCornerShape(8.dp),
                colors = buttonColors,
                enabled = isEnableButton,
            ) {
                AppText(
                    text = "Tiếp tục",
                    style = ProductXTheme.typography.SemiBold.Title.Medium,
                    color = color
                )
            }
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