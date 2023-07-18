package com.example.superapp.data.signUp

sealed class SignUpUiEvent {

    data class NameChanged(val name: String) : SignUpUiEvent()
    data class EmailChanged(val email: String) : SignUpUiEvent()
    data class PasswordChanged(val password: String) : SignUpUiEvent()
    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : SignUpUiEvent()

    object RegisterButtonClicked : SignUpUiEvent()
    object GoogleButtonClicked : SignUpUiEvent()

}
