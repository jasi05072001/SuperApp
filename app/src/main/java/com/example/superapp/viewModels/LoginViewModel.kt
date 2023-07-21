package com.example.superapp.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    val isLoading = mutableStateOf(false)
    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String) {
        isLoading.value = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading.value = false
                if (task.isSuccessful) {
                    _message.value = "Welcome back!"

                    AppRouter.navigateTo(Screen.HomeScreen)

                }
            }
            .addOnFailureListener {
                isLoading.value = false
                _message.value = it.message

            }
    }

    fun resetPasswordLink(email: String) {
        isLoading.value = true
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isLoading.value = false
                    _message.value = "Reset password link sent to your email."
                    viewModelScope.launch {
                        delay(2000)
                        AppRouter.navigateTo(Screen.SignUpScreen)
                    }

                } else {
                    _message.value = task.exception?.message ?: "Unknown error occurred."
                    isLoading.value = false
                }
            }
    }
}