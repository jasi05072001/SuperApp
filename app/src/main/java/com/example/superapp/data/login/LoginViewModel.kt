package com.example.superapp.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.superapp.data.rules.Validator
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {

    private var loginUiState = mutableStateOf(LoginUiState())

    var allValidationsPassed = mutableStateOf(false)

    var loginProgress = mutableStateOf(false)

    fun onEvent(event: LoginUiEvent){
        validateDataWithRules()
        when(event){
            is LoginUiEvent.EmailChanged -> {
                loginUiState.value = loginUiState.value.copy(email = event.email)
            }
            is LoginUiEvent.PasswordChanged -> {
                loginUiState.value = loginUiState.value.copy(password = event.password)
            }
            is LoginUiEvent.LoginButtonClicked -> {
                login()
            }
        }
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUiState.value.password
        )

        loginUiState.value = loginUiState.value.copy(
            isEmailValid = emailResult.status,
            isPasswordValid = passwordResult.status,
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }

    private fun login() {
        loginProgress.value = true
        val email = loginUiState.value.email
        val password = loginUiState.value.password

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                loginProgress.value = false

                if (task.isSuccessful){
                    AppRouter.navigateTo(Screen.HomeScreen)
                }

            }
            .addOnFailureListener {
                loginProgress.value = false
                Log.d("Error", "login:  ${it.message}")

            }
    }
}