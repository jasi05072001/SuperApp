package com.example.superapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.superapp.app.SuperApp
import com.example.superapp.ui.theme.SuperAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperApp()
//                    Greeting()
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Greeting(restPass: RestPass = viewModel()) {
//    val email = remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    val resetPasswordError by restPass.resetPasswordError.observeAsState()
//    val isLoading = remember { mutableStateOf(false) }
//
//    Column {
//        TextField(
//            value = email.value,
//            onValueChange = { email.value = it },
//            label = { Text("Email") },
//            leadingIcon = {
//                Icons.Default.Email
//            },
//            modifier = Modifier.wrapContentSize()
//        )
//        Button(
//            onClick = {
//                isLoading.value = true
//                restPass.resetPassword(email.value)
//            },
//            modifier = Modifier.padding(vertical = 16.dp)
//        ) {
//            Text("Reset Password")
//        }
//
//        if (isLoading.value) {
//            LinearProgressIndicator(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 8.dp)
//            )
//        }
//
//        if (!resetPasswordError.isNullOrEmpty()) {
//            isLoading.value = false
//            LaunchedEffect(resetPasswordError) {
//                showToast(context, resetPasswordError!!)
//            }
//        }
//    }
//}

//class RestPass() : ViewModel() {
//
//    private val _resetPasswordError = MutableLiveData<String>()
//    val resetPasswordError: LiveData<String> get() = _resetPasswordError
//
//
//    fun resetPassword(email: String) {
//
//
//        FirebaseAuth.getInstance()
//            .sendPasswordResetEmail(email)
//            .addOnCompleteListener { task ->
//                _resetPasswordError.value = "Email sent."
//            }
//            .addOnFailureListener { exception ->
//                _resetPasswordError.value = exception.message
//                Log.d("Error", "resetPassword: ${exception.message}")
//            }
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperAppTheme {

    }
}