package com.example.superapp.data.signUp

data class RegistrationUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val privacyPolicyAccepted: Boolean = false,

    var isNameValid: Boolean = false,
    var isEmailValid: Boolean = false,
    var isPasswordValid: Boolean = false,
    var privacyPolicyError : Boolean = false,
)
