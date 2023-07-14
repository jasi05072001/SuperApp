package com.example.superapp.data.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",

    var isEmailValid: Boolean = false,
    var isPasswordValid: Boolean = false,

)
