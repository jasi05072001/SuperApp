package com.example.superapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.superapp.components.ButtonComponent
import com.example.superapp.data.SignUpViewModel

@Composable
fun HomeScreen(signUpViewModel: SignUpViewModel= viewModel()) {

    Column (
        Modifier
            .fillMaxSize()

    ){

        ButtonComponent(
            value = "LogOut",
            onClick = {
                signUpViewModel.logOut()

            },
            isEnabled = true
        )
    }
}