package com.example.superapp.screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superapp.R
import com.example.superapp.components.AppFonts
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {

    var isVisible by remember { mutableStateOf(false) }

    val uiColor =
        if (isSystemInDarkTheme())
            Color(0xff1E293B)
        else
            Color(0xffBFDBFE)
    val textColor =
        if (isSystemInDarkTheme())
            Color.White
        else
            Color.Black

    LaunchedEffect(Unit) {
        isVisible = true
        delay(3000)

        AppRouter.navigateTo(Screen.SignUpScreen)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                uiColor
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(1450, easing = EaseIn)
            ) + fadeIn(animationSpec = tween(1450, easing = EaseOut)
            ),

        ) {

           Image(
               painter = painterResource(id = R.drawable.aura_icon),
               contentDescription = "App Icon",
           )


        }
        Spacer(modifier = Modifier.height(40.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(1450, easing = {
                    // Sinusoidal easing
                    val pi = 3.14f
                    (-0.5f * kotlin.math.cos(it * pi / 1.0f) + 0.5f)
                }
                )
            ) + fadeIn(
                animationSpec = tween(1450, easing =
                //EaseOutExpo
                {
                    // Sinusoidal easing
                    val pi = 3.14f
                    (-0.5f * kotlin.math.cos(it * pi / 1.0f) + 0.5f)
                }
                )
            ),
        ) {

                Text(
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = textColor,
                        fontFamily = AppFonts.fontFamily
                    ),
                )


        }


    }

}