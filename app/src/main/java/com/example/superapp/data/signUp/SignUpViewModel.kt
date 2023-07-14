package com.example.superapp.data.signUp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.superapp.data.rules.Validator
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel: ViewModel() {

    private var registrationUiState = mutableStateOf(RegistrationUiState())
    var allValidationsPassed = mutableStateOf(false)
    var signUpProgress = mutableStateOf(false)


    fun onEvent(event: SignUpUiEvent){
        validateDataWithRules()
        when(event){
            is SignUpUiEvent.NameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(name = event.name)
            }
            is SignUpUiEvent.EmailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(email = event.email)

            }
            is SignUpUiEvent.PasswordChanged -> {
                registrationUiState.value = registrationUiState.value.copy(password = event.password)

            }
            is SignUpUiEvent.RegisterButtonClicked -> {
                signUp()
            }
//            is UiEvent.PrivacyPolicyCheckBoxClicked -> {
//                registrationUiState.value = registrationUiState.value.copy(
//                    privacyPolicyAccepted = event.status
//                )
//            }
        }

    }

    private fun signUp() {
        createAccount(
            email =registrationUiState.value.email ,
            password =registrationUiState.value.password
        )
    }

    private fun validateDataWithRules() {

        val nameResult = Validator.validateName(
            name = registrationUiState.value.name
        )
        val emailResult = Validator.validateEmail(
            email = registrationUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUiState.value.password
        )

//        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
//            statusValue  = registrationUiState.value.privacyPolicyAccepted
//        )

        registrationUiState.value = registrationUiState.value.copy(
            isNameValid = nameResult.status,
            isEmailValid = emailResult.status,
            isPasswordValid = passwordResult.status,
            // privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPassed.value =
            nameResult.status
                    &&
                    emailResult.status
                    &&
                    passwordResult.status
//                    &&
//                    privacyPolicyResult.status

    }

    private fun createAccount(email:String, password:String){
        signUpProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task->
                Log.d("Success", "createAccount:${task.isSuccessful} ")
                signUpProgress.value = false

                if (task.isSuccessful){
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {exception ->
                signUpProgress.value = false
                Log.d("Failure", "createAccount: ${exception.message} ")

            }
    }

}
