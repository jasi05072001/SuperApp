package com.example.superapp.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen{

    object SplashScreen: Screen()
    object SignUpScreen: Screen()
    object SignInScreen: Screen()
    object TermsAndConditionsScreen: Screen()
    object HomeScreen: Screen()

}

object AppRouter{
    var currentScreen : MutableState<Screen> = mutableStateOf(Screen.SplashScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}