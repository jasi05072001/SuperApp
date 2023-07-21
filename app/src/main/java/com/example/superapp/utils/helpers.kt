package com.example.superapp.utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

object FirebaseConst {
    const val userCollections = "users"

}

object HelperFunction {
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 6
    }

    fun isNameValid(name: String): Boolean {
        return name.isNotEmpty() && name.length >= 3
    }

    fun isCheckBoxChecked(checked: Boolean): Boolean {
        return checked
    }
}
