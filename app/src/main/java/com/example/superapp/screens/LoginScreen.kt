package com.example.superapp.screens

import android.util.Log
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superapp.R
import com.example.superapp.components.AlreadyHaveAccountComponent
import com.example.superapp.components.ButtonComponent
import com.example.superapp.components.DividerTextComponent
import com.example.superapp.components.EmailFieldComponent
import com.example.superapp.components.HeadingTextComponent
import com.example.superapp.components.LoaderComponent
import com.example.superapp.components.NormalTextComponent
import com.example.superapp.components.OtherLoginOptionsComponent
import com.example.superapp.components.PasswordFieldComponent
import com.example.superapp.components.UnderLinedClickableTextComponent
import com.example.superapp.data.UserInfo
import com.example.superapp.data.google.rememberFirebaseAuthLauncher
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.example.superapp.navigation.SystemBackButtonHandler
import com.example.superapp.utils.HelperFunction
import com.example.superapp.utils.rememberImeState
import com.example.superapp.utils.showToast
import com.example.superapp.viewModels.LoginViewModel
import com.example.superapp.viewModels.SignUpViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignInScreen(loginViewModel: LoginViewModel = viewModel()) {

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val uiColor = if (isSystemInDarkTheme()) Color(0xff1E293B) else Color(0xffBFDBFE)

    Surface(color = uiColor) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            MainLayout(loginViewModel)
        }
    }
    LaunchedEffect(key1 =imeState.value){
        if(imeState.value){
            scrollState.animateScrollTo(
                scrollState.value + 200,
                animationSpec = tween(1000, easing = EaseInOut)
            )
        }
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignUpScreen)
    }

}

@Composable
private fun MainLayout(loginViewModel: LoginViewModel) {

    val loginError by loginViewModel.message.observeAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val signUpViewModel: SignUpViewModel = viewModel()

    val isLoading = remember {
        mutableStateOf(false)
    }
    val token = stringResource(R.string.default_web_client_id)

    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
    }
    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, gso)
    }

    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            user = result.user
            val info = UserInfo(
                name = user!!.displayName,
                email = user!!.email,
                imgUrl = user!!.photoUrl.toString(),
                uid = user!!.uid
            )
            signUpViewModel.saveUserInfo(info)
            isLoading.value = false
            AppRouter.navigateTo(Screen.HomeScreen)

        },
        onAuthError = {
            user = null
            isLoading.value = false
            showToast(context, "Sign in failed")
        }
    )


    Column(
        Modifier
            .padding(horizontal = 15.dp)
            .fillMaxHeight(),
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        NormalTextComponent(
            value = stringResource(id = R.string.hello)
        )
        HeadingTextComponent(
            value = stringResource(id = R.string.welcome_back)
        )
        Spacer(modifier = Modifier.height(20.dp))

        NormalTextComponent(
            value = stringResource(id = R.string.login)
        )
        Spacer(modifier = Modifier.height(10.dp))

        EmailFieldComponent(
            labelValue = "Email",
            icon = Icons.Outlined.Email,
            imeAction = ImeAction.Next,
            onValueChange = {
                email = it
            },
            value = email,

            )
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        PasswordFieldComponent(
            labelValue = stringResource(id = R.string.password),
            icon = Icons.Outlined.Lock,
            imeAction = ImeAction.Done,
            onValueChange = {
                password = it
            },
            value = password,
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        UnderLinedClickableTextComponent(value = "Forgot Password?") {
            AppRouter.navigateTo(Screen.ForgotPasswordScreen)

        }

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        ButtonComponent(
            value = stringResource(R.string.login),
            onClick = {
                isLoading.value = true
                loginViewModel.login(email, password)
                isLoading.value = false

            },
            isEnabled = HelperFunction.isValidEmail(email) && HelperFunction.isValidPassword(
                password
            )
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )

        DividerTextComponent()
        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            OtherLoginOptionsComponent(
                image = painterResource(id = R.drawable.ic_google),
                onClick = {
                    isLoading.value = true
                    launcher.launch(googleSignInClient.signInIntent)
                }
            )
            Spacer(
                modifier = Modifier.width(20.dp)
            )
            OtherLoginOptionsComponent(
                image = painterResource(id = R.drawable.ic_facebook),
                onClick = { Log.d("TAG", "BottomSection: facebook") })
        }
        Spacer(
            modifier = Modifier.height(30.dp)
        )

        AlreadyHaveAccountComponent(
            startValue = "Don't have an Account yet? ",
            clickableValue = stringResource(id = R.string.sign_up),

            ) {
            AppRouter.navigateTo(Screen.SignUpScreen)
        }
        Spacer(
            modifier = Modifier.height(10.dp)
        )

    }

    if (!loginError.isNullOrEmpty()) {
        LaunchedEffect(loginError) {
            showToast(context, loginError!!)
        }
    }
    if (loginViewModel.isLoading.value || isLoading.value) {
        LoaderComponent()
    }


}

@Preview(device = Devices.PIXEL_XL)
@Composable
private fun DefaultPreview() {
    MainLayout(loginViewModel = LoginViewModel())
}