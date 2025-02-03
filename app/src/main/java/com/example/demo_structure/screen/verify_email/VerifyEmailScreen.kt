package com.example.demo_structure.screen.verify_email

import android.content.Intent
import android.net.Uri
import android.util.Patterns
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo_structure.R
import com.example.demo_structure.core.component.EmailTextField
import com.example.demo_structure.screen.education.EducationViewModel
import com.example.demo_structure.util.FormatText.buildClickableText
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun VerifyEmailRoute(
    viewModel: VerifyEmailViewModel,
    onNavigateLogin: (String) -> Unit,
) {
    val emailState by viewModel.menuUiState.collectAsStateWithLifecycle()
    VerifyEmailScreen(
        modifier = Modifier.fillMaxSize(),
        state = emailState
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    contentView(Modifier)
}


@Composable
internal fun VerifyEmailScreen(state: EmailState, modifier: Modifier = Modifier) {
    contentView(modifier = modifier)
}

@Composable
fun contentView(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .padding(top = 48.dp)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        val context = LocalContext.current
        var email by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf("") }
        var isChecked by remember { mutableStateOf(false) }

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
            Modifier.constrainAs(textview) {
                top.linkTo(logo.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        EmailTextField(
            modifier = Modifier
                .padding(16.dp)
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
            },
            error = emailError,
            onClose = { email = "" }
        )

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
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = Color.Blue,
                        uncheckedColor = Color.Gray
                    )
                )
                val annotatedString = buildClickableText(
                    text = "Bằng việc click vào ô này, bạn đã đồng ý với Điều khoản dịch vụ và chính sách bảo mật của X",
                    clickableText = "Điều khoản dịch vụ và chính sách bảo mật",
                    tag = "tag_name",
                    color = colorResource(R.color.violets_are_blue),
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    onClick = {
                        context.startActivity(intent)
                    }
                )

                BasicText(text = annotatedString, Modifier.weight(1f))
            }


            fun verifyEmail(){
                emailError = if (email.isEmpty() || !isValidEmail(email)) "Invalid email format" else ""
                if(emailError.isNullOrEmpty()){

                }
            }

            Button(
                onClick = {
                    verifyEmail()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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