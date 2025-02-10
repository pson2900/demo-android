package com.example.demo_structure.screen.verify_email

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
import androidx.compose.material3.Text
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demo_structure.R
import com.example.demo_structure.core.component.EmailTextField
import com.example.demo_structure.screen.home.LoadingState
import com.example.demo_structure.screen.otp.OTPType
import com.example.demo_structure.util.extension.buildClickableText



@Preview(showBackground = true)
@Composable
private fun VerifyEmailPreview() {
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
        onLinkClick = {}
    )
}

@Composable
fun VerifyEmailScreen(
    viewModel: VerifyEmailViewModel = viewModel(),
    onNavigateToVerifyOtp: (String, String) -> Unit,
    onNavigateToLogin: (String) -> Unit
) {
    val context = LocalContext.current
    val emailState by viewModel.emailUiState.collectAsStateWithLifecycle()

    var email by rememberSaveable { mutableStateOf(viewModel.email) }
    var isChecked by rememberSaveable { mutableStateOf(viewModel.isChecked) }

    var emailError by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val isEnableButton = email.isNotEmpty() && isChecked && !isLoading

    val lifecycleOwner = LocalLifecycleOwner.current



    val intent =
        remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://staging.vietnamworks.com/")) }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun verifyEmail() {
        emailError =
            if (email.isEmpty() || !isValidEmail(email)) "Invalid email format" else ""
        if (emailError.isEmpty()) {
            viewModel.verifyEmail(email)
        }
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
        emailError = ""
        isSuccess = newEmail.isNotEmpty() && isValidEmail(newEmail)
    }

    fun onCheckboxChange(newChecked: Boolean) {
        isChecked = newChecked
    }

    fun onButtonClick() {
       verifyEmail()
    }

    fun onLinkClick() {
        context.startActivity(intent)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.clearEmailState()
            }
            if(event ==  Lifecycle.Event.ON_DESTROY){
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
            is EmailState.Loading -> {
                isLoading = state.isLoading
            }

            is EmailState.Success -> {
                isLoading = false
                if (state.found == true) {
                    onNavigateToLogin(email)
                } else {
                    onNavigateToVerifyOtp(email, OTPType.REGISTER.type)
                }
            }

            is EmailState.Error -> {
                isLoading = false
            }
            else -> Unit
        }
    }

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
        onLinkClick = ::onLinkClick
    )
}

fun hideKeyboardAndClearFocus(
    context: Context,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?
) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = null
    if (context is android.app.Activity) {
        view = context.currentFocus
    }
    if (view == null) {
        view = View(context)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    focusManager.clearFocus()
    keyboardController?.hide()
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
    onLinkClick: () -> Unit
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
            disabledContentColor = colorResource(id = R.color.tuna)
        )

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
        )
        Text(
            text = "Khám phá con đường sự nghiệp của riêng bạn",
            modifier = Modifier
                .constrainAs(textview) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 24.dp, end = 24.dp),
            style = TextStyle(fontSize = 24.sp),
            textAlign = TextAlign.Center
        )

        EmailTextField(
            modifier = Modifier
                .padding(24.dp)
                .constrainAs(emailTextField) {
                    top.linkTo(textview.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            hint = "Email của bạn",
            value = email,
            onValueChange = onEmailChange,
            error = emailError,
            onClose = { onEmailChange("") },
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
                        .height(24.dp),
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
                    text = annotatedString, Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 24.sp
                    )
                )
            }

            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = buttonColors,
                enabled = isEnableButton
            ) {
                Text(text = "Tiếp tục")
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