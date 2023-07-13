package com.example.superapp.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.example.superapp.screens.SignInScreen
import com.example.superapp.screens.SignUpScreen
import com.example.superapp.screens.SplashScreen
import com.example.superapp.screens.TermsAndConditionsScreen


@Composable
fun SuperApp() {
    val uiColors = if (isSystemInDarkTheme()) Color.Black else Color.White
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(uiColors),
    ){
        val currentScreen = AppRouter.currentScreen

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val splashScreenVisible = currentScreen.value is Screen.SplashScreen
            val signUpScreenVisible = currentScreen.value is Screen.SignUpScreen
            val signInScreenVisible = currentScreen.value is Screen.SignInScreen
            val termsScreenVisible = currentScreen.value is Screen.TermsAndConditionsScreen

            AnimatedVisibility(
                visible= splashScreenVisible,
                enter = fadeIn() + slideInHorizontally(initialOffsetX = { -it }),
                exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it })
            ) {
                SplashScreen()
            }

            AnimatedVisibility(
                visible = signUpScreenVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { -it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
            ) {
                SignUpScreen()
            }
            AnimatedVisibility(
                visible = signInScreenVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { -it })
            ) {
                SignInScreen()
            }

            AnimatedVisibility(
                visible = termsScreenVisible,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { -it })
            ) {
                TermsAndConditionsScreen()
            }
        }
    }
}



