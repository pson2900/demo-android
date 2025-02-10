package com.example.demo_structure.screen.verify_email

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.core.component.EmailTextField
import com.example.demo_structure.util.extension.buildClickableText


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    contentView(Modifier)
}


@Composable
internal fun VerifyEmailScreen(
    viewModel: VerifyEmailViewModel,
    onNavigateToVerifyOtp: (String) -> Unit,
    onNavigateToLogin: (String) -> Unit
) {
    contentView(modifier = Modifier, onNavigateToVerifyOtp = onNavigateToVerifyOtp)
}

@Composable
private fun contentView(
    modifier: Modifier = Modifier,
    onNavigateToVerifyOtp: ((String) -> Unit)? = null
) {
    fun hideKeyboardAndClearFocus(activity: Activity, focusManager: FocusManager, keyboardController: androidx.compose.ui.platform.SoftwareKeyboardController?) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        focusManager.clearFocus()
        keyboardController?.hide()
    }

//    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusManager = LocalFocusManager.current
//    val activity = LocalContext.current as Activity

    ConstraintLayout(
        modifier = modifier
//            .pointerInput(Unit) {
//                detectTapGestures(onTap = {
//                    hideKeyboardAndClearFocus(activity, focusManager, keyboardController)
//                })
//            }
            .padding(top = 48.dp)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        val context = LocalContext.current
        var email by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf("") }
        var isChecked by remember { mutableStateOf(false) }
        var isSuccess by remember { mutableStateOf(false) }

        val buttonColors = ButtonColors(
            containerColor = colorResource(id = R.color.royal_blue),
            contentColor = Color.White, // Default text color
            disabledContainerColor = colorResource(id = R.color.jumbo), // Disabled background color
            disabledContentColor = colorResource(id = R.color.tuna)// Disabled text color
        )
        var isEnableButton by remember { mutableStateOf(false) }
        isEnableButton = email.isNotEmpty() && isChecked

        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")) }

        val (logo, textview, emailTextField, columnBottom) = createRefs()

        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        Image(
            painter = painterResource(id = R.drawable.ic_rondy_stickers), // Load image from drawable
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
        Text(text = "Khám phá con đường sự nghiệp của riêng bạn",
            Modifier
                .constrainAs(textview) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 24.dp, end = 24.dp), style = TextStyle(fontSize = 24.sp),
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
            onValueChange = {
                email = it
                emailError = ""
                isSuccess = it.isNotEmpty() && isValidEmail(it)
            },
            error = emailError,
            onClose = { email = "" },
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
                    onCheckedChange = { isChecked = it },
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
                    textLinkStyles = TextLinkStyles(
                        style = SpanStyle(
                            color = colorResource(R.color.violets_are_blue),
                            fontSize = 17.sp
                        )
                    ),
                    onClick = {
                        context.startActivity(intent)
                    }
                )

                BasicText(text = annotatedString, Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                    style = TextStyle(fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 24.sp)
                )
            }


            fun verifyEmail() {
                emailError = if (email.isEmpty() || !isValidEmail(email)) "Invalid email format" else ""
                if (emailError.isNullOrEmpty()) {
                    onNavigateToVerifyOtp?.invoke(email)
                }
            }

            Button(
                onClick = {
                    verifyEmail()
                }, modifier = Modifier
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
    }
}