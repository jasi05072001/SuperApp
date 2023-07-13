package com.example.superapp.data

data class LoginUiState(
    val email: String = "",
    val password: String = "",

    var isEmailValid: Boolean = false,
    var isPasswordValid: Boolean = false,

)
