package com.example.demo_structure.screen.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.demo_structure.R
import com.example.demo_structure.core.component.AppPreviewWrapper
import com.example.demo_structure.core.component.CountdownTextView
import com.example.demo_structure.core.component.otp.OTPTextField
import com.example.demo_structure.core.component.otp.OtpTextFieldDefaults
import com.example.demo_structure.screen.verify_email.VerifyEmailViewModel
import com.example.demo_structure.util.FormatText.buildClickableText

@Preview(showBackground = true)
@Composable
fun OTPScreenPreview() {
    AppPreviewWrapper {
        contentView(it, "demo@gmail.com", "origin")
    }
}


@Composable
fun VerifyOTPScreen(viewModel: VerifyEmailViewModel, email: String, origin: String) {
    contentView(Modifier, email, origin)
}

@Composable
private fun contentView(modifier: Modifier = Modifier, email: String, origin: String) {
    ConstraintLayout(
        modifier = modifier
            .padding(top = 48.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        val (emailTextview, textViewDescription, otpTextField, textViewError, columnBottom) = createRefs()
        val label = "Mã xác nhận đã được gửi qua email $email"
        var otp by remember { mutableStateOf("") }
        var isResend by remember { mutableStateOf(false) }
        var isError by remember { mutableStateOf(false) }

        val annotatedString = buildClickableText(
            text = label,
            clickableText = email,
            tag = "tag_name",
            style = SpanStyle(
                color = Color.Black,
                fontSize = 24.sp
            ),
            textLinkStyles = TextLinkStyles(
                style = SpanStyle(
                    color = colorResource(R.color.violets_are_blue),
                    fontSize = 24.sp
                )
            ),
            onClick = {

            }
        )

        BasicText(text = annotatedString,
            Modifier.constrainAs(emailTextview) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Text(
            modifier = Modifier
                .constrainAs(textViewDescription) {
                    top.linkTo(emailTextview.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 8.dp),
            text = "Hãy kiểm tra hộp thư Chính hoặc Spam trong mail của bạn nhé"
        )

        fun checkOTP(otp: String) {
            if (otp.length == 4) {
                isError = false
                if (otp == "1234") {

                } else {
                    isError = true
                }
            }
        }

        OTPTextField(
            modifier = Modifier
                .width(260.dp)
                .constrainAs(otpTextField) {
                    top.linkTo(textViewDescription.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 32.dp),
            value = otp, // Initial value
            onTextChanged = {
                otp = it
                checkOTP(it)
            },
            numDigits = 4, // Number of digits in OTP
            isMasked = false, // Mask digits for security
            digitContainerStyle = OtpTextFieldDefaults.outlinedContainer(size = 24.dp), // Choose style (outlined or underlined)
            textStyle = MaterialTheme.typography.titleLarge, // Configure text style
            isError = isError // Indicate whether the OTP field is in an error state
        )


        if (isError) {
            Text(
                modifier = Modifier
                    .constrainAs(textViewError) {
                        top.linkTo(otpTextField.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 24.dp),
                style = TextStyle(color = Color.Red, fontSize = 16.sp),
                text = "Sai OTP"
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(columnBottom) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            if (isResend) {
                val resendString = buildClickableText(
                    text = "Chưa nhận được mã? Gửi lại",
                    clickableText = "Gửi lại",
                    tag = "tag_name",
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    textLinkStyles = TextLinkStyles(
                        style = SpanStyle(
                            color = colorResource(R.color.violets_are_blue),
                            fontSize = 16.sp
                        )
                    ),
                    onClick = {
                        isResend = false
                    }
                )

                BasicText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp),
                    text = resendString,
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            } else {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Chưa nhận được mã? ",
                        style = TextStyle(color = colorResource(R.color.black), fontSize = 16.sp)
                    )
                    CountdownTextView(Modifier.wrapContentWidth(),
                        value = "Gửi lại",
                        style = TextStyle(color = colorResource(R.color.boulder), fontSize = 16.sp),
                        minutesState = 1f,
                        onCountdownFinished = {
                            isResend = true
                        })
                }
            }
        }
    }
}