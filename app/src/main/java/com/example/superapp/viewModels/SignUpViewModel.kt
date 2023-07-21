package com.example.superapp.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superapp.R
import com.example.superapp.data.UserInfo
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.example.superapp.utils.FirebaseConst
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpViewModel: ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    val isLoading = mutableStateOf(false)
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore


    fun register(email: String, password: String, context: Context, name: String) {
        val appName = context.getString(R.string.app_name)

        isLoading.value = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading.value = false
                if (task.isSuccessful) {
                    _message.value = "Welcome to $appName !"
                    saveUserInfo(
                        userInfo = UserInfo(
                            name = name,
                            email = email,
                            imgUrl = "",
                            uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
                        )
                    )

                }
            }
            .addOnFailureListener {
                isLoading.value = false
                _message.value = it.message

            }
    }

    fun saveUserInfo(userInfo: UserInfo) {
        db.collection(FirebaseConst.userCollections)
            .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .set(userInfo)
            .addOnSuccessListener {
                AppRouter.navigateTo(Screen.HomeScreen)
            }
            .addOnFailureListener { exception ->
                Log.d("Failure", "saveUserInfo: ${exception.message} ")
            }
    }

}
