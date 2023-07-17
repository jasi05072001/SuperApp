package com.example.superapp.screens

import android.util.Log
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superapp.R
import com.example.superapp.components.AlreadyHaveAccountComponent
import com.example.superapp.components.ButtonComponent
import com.example.superapp.components.CheckBoxComponent
import com.example.superapp.components.DividerTextComponent
import com.example.superapp.components.HeadingTextComponent
import com.example.superapp.components.LoaderComponent
import com.example.superapp.components.NormalTextComponent
import com.example.superapp.components.OtherLoginOptionsComponent
import com.example.superapp.components.PasswordFieldComponent
import com.example.superapp.components.TextFieldComponent
import com.example.superapp.data.signUp.SignUpUiEvent
import com.example.superapp.data.signUp.SignUpViewModel
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.example.superapp.rememberImeState

@Composable
fun SignUpScreen() {

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val  uiColor = if (isSystemInDarkTheme())Color(0xff1E293B) else Color(0xffBFDBFE)
    val signUpViewModel = viewModel<SignUpViewModel>()

    Surface(color = uiColor) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            MainLayout(
                signUpViewModel
            )
        }
    }
    LaunchedEffect(key1 =imeState.value){
        if(imeState.value){
            scrollState.animateScrollTo(
                scrollState.value + 500,
                animationSpec = tween(1000, easing = EaseInOut)
            )
        }
    }
}

@Composable
private fun MainLayout(signUpViewModel: SignUpViewModel) {

    Column(Modifier.padding(horizontal = 15.dp)) {

        Spacer(modifier = Modifier.height(25.dp))

        NormalTextComponent(
            value = stringResource(id = R.string.hello)
        )
        HeadingTextComponent(
            value = stringResource(id = R.string.create_account)
        )
        Spacer(modifier = Modifier.height(20.dp))

        NormalTextComponent(
            value = stringResource(id = R.string.sign_up)
        )
        Spacer(modifier = Modifier.height(10.dp))


        TextFieldComponent(
            labelValue = stringResource(id = R.string.name),
            icon = Icons.Outlined.PersonOutline,
            onTextSelected = {
                signUpViewModel.onEvent(SignUpUiEvent.NameChanged(it))

            }
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        TextFieldComponent(
            labelValue = stringResource(id = R.string.email),
            icon = Icons.Outlined.Email,
            isEmail = true,
            onTextSelected = {
                signUpViewModel.onEvent(SignUpUiEvent.EmailChanged(it))

            }
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        PasswordFieldComponent(
            labelValue = stringResource(id = R.string.password),
            icon = Icons.Outlined.Lock,
            onTextSelected = {
                signUpViewModel.onEvent(SignUpUiEvent.PasswordChanged(it))
            }
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        CheckBoxComponent(
            onTextSelected = {
                AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
            },
            onCheckedChange = {
                Log.d("TAG13", "MainLayout: $it")
                signUpViewModel.onEvent(SignUpUiEvent.PrivacyPolicyCheckBoxClicked(it))
                Log.d(
                    "TAG14",
                    "MainLayout:${
                        signUpViewModel.onEvent(
                            SignUpUiEvent.PrivacyPolicyCheckBoxClicked(it)
                        )
                    } "
                )
            }
        )
        Spacer(modifier = Modifier.height(15.dp))

        ButtonComponent(
            value = stringResource(R.string.register),
            isEnabled = signUpViewModel.allValidationsPassed.value,
            onClick = {
                signUpViewModel.onEvent(SignUpUiEvent.RegisterButtonClicked)
            },
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        DividerTextComponent()

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            OtherLoginOptionsComponent(
                image = painterResource(id = R.drawable.ic_google),
                onClick = { Log.d("TAG", "BottomSection: google") }
            )
            Spacer(
                modifier = Modifier.width(20.dp)
            )
            OtherLoginOptionsComponent(
                image = painterResource(id = R.drawable.ic_facebook),
                onClick = { Log.d("TAG", "BottomSection: facebook") })
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )

        AlreadyHaveAccountComponent(
            startValue = "Already have an Account? ",
            clickableValue = "Login",

            ) {
            AppRouter.navigateTo(Screen.SignInScreen)
        }
        if (signUpViewModel.signUpProgress.value){
            LoaderComponent()

        }

    }

}

@Preview(device = Devices.PIXEL_XL)
@Composable
private fun DefaultPreview() {
    SignUpScreen()
}