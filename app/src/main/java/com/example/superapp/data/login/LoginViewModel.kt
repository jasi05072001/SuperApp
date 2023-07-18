package com.example.superapp.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superapp.data.rules.Validator
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {


    private val _resetPasswordError = MutableLiveData<String>()
    val resetPasswordError: LiveData<String> get() = _resetPasswordError


    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> get() = _loginError


    private var loginUiState = mutableStateOf(LoginUiState())

    var allValidationsPassed = mutableStateOf(false)

    var loginProgress = mutableStateOf(false)

    var emailValidation = mutableStateOf(false)


    fun onEvent(event: LoginUiEvent) {
        validateDataWithRules()
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                loginUiState.value = loginUiState.value.copy(email = event.email)
            }

            is LoginUiEvent.PasswordChanged -> {
                loginUiState.value = loginUiState.value.copy(password = event.password)
            }

            is LoginUiEvent.LoginButtonClicked -> {
                login()
            }

            is LoginUiEvent.ForgotPasswordClicked -> {
                resetPassword()
            }
        }
    }

    private fun resetPassword() {
        val email = loginUiState.value.email

        FirebaseAuth.getInstance()
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _resetPasswordError.value = "Email sent."
                }
            }
            .addOnFailureListener { exception ->
                _resetPasswordError.value = exception.message
                Log.d("Error", "resetPassword: ${exception.message}")
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

        emailValidation.value = emailResult.status
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
                _loginError.value = it.message

            }
    }
}