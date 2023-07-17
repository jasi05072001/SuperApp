package com.example.superapp.screens

import android.app.Activity
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superapp.R
import com.example.superapp.components.AppFonts
import com.example.superapp.components.ButtonComponent
import com.example.superapp.components.TextFieldComponent
import com.example.superapp.data.login.LoginUiEvent
import com.example.superapp.data.login.LoginViewModel
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.example.superapp.navigation.SystemBackButtonHandler
import com.example.superapp.rememberImeState
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(loginViewModel: LoginViewModel = viewModel()) {
    val uiColor = if (isSystemInDarkTheme()) Color(0xff1E293B) else Color(0xffBFDBFE)
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val iconColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val toolBarColor = if (isSystemInDarkTheme()) Color(0xff1E293B) else Color(0xffBFDBFE)


    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignInScreen)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Forgot Password",
                        textAlign = TextAlign.Center,
                        color = iconColor,
                        fontFamily = AppFonts.fontFamily
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            AppRouter.navigateTo(Screen.SignInScreen)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = iconColor
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = toolBarColor),

                )


        }
    ) { paddingValues ->

        Surface(color = uiColor, modifier = Modifier.padding(paddingValues)) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                ForgotPassWordView(loginViewModel)
            }
        }

    }
    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(
                scrollState.value + 500,
                animationSpec = tween(1000, easing = EaseInOut)
            )
        }
    }
}


@Composable
fun ForgotPassWordView(loginViewModel: LoginViewModel) {
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {

        Text(
            text = "Enter your email address and we'll send you a link to reset your password",
            color = textColor,
            modifier = Modifier.padding(top = 15.dp, bottom = 10.dp),
            fontFamily = AppFonts.fontFamily
        )

        Image(
            painter = painterResource(id = R.drawable.img_forgot_password),
            contentDescription = "Forgot password illustration",
            modifier = Modifier
                .padding(all = 10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldComponent(
            labelValue = "Email",
            icon = Icons.Outlined.Email,
            onTextSelected = {
                loginViewModel.onEvent(LoginUiEvent.EmailChanged(it))

            },
            isEmail = true,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        ButtonComponent(
            value = "Continue",
            onClick = {
                loginViewModel.onEvent(LoginUiEvent.ForgotPasswordClicked)
            },
            isEnabled = loginViewModel.emailValidation.value
        )

        Spacer(modifier = Modifier.height(20.dp))

    }

    if (loginViewModel.loginProgress.value) {
        MotionToast.createToast(
            context as Activity,
            "Reset link sent",
            "Password reset link has been sent to your email address",
            MotionToastStyle.SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.roboto_seriff)
        )

    }


}
