package com.example.superapp.data

sealed class SignUpUiEvent{

    data class NameChanged(val name:String):SignUpUiEvent()
    data class EmailChanged(val email:String):SignUpUiEvent()
    data class PasswordChanged(val password:String):SignUpUiEvent()
//    data class PrivacyPolicyCheckBoxClicked(val status:Boolean):UiEvent()

    object RegisterButtonClicked:SignUpUiEvent()

}
