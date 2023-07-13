package com.example.superapp.data.rules

object Validator {

    fun validateName(name:String):ValidationResult{
        return ValidationResult(name.trim().isNotEmpty() && name.trim().length>=5)
    }

    fun validateEmail(email:String):ValidationResult{
        return  ValidationResult(email.trim().isNotEmpty())
    }
    fun validatePassword(password:String):ValidationResult{
        return ValidationResult(password.isNotEmpty() && password.length>=6)
    }
//    fun validatePrivacyPolicyAcceptance(statusValue:Boolean):ValidationResult{
//        return ValidationResult(statusValue)
//    }
}

data class ValidationResult(
    val status:Boolean = false
)