package com.example.superapp.data.home

import androidx.lifecycle.ViewModel
import com.example.superapp.R
import com.example.superapp.data.NavigationItem
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {

    fun logOut() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                AppRouter.navigateTo(Screen.SignUpScreen)
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    val navigationItemsList = listOf<NavigationItem>(

        NavigationItem(
            title = "Home",
            description = "Home",
            itemId = "homeScreen",
            icon = R.drawable.ic_home
        ),
        NavigationItem(
            title = "Settings",
            description = "Settings",
            itemId = "settingsScreen",
            icon = R.drawable.ic_settings
        ),
        NavigationItem(
            title = "Favorites",
            description = "Favorites",
            itemId = "favoritesScreen",
            icon = R.drawable.ic_fav
        ),

        )
}