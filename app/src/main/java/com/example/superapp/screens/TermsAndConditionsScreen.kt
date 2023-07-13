package com.example.superapp.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat.startActivity
import com.example.superapp.R
import com.example.superapp.components.AppFonts
import com.example.superapp.components.CircleAvatar
import com.example.superapp.navigation.AppRouter
import com.example.superapp.navigation.Screen
import com.example.superapp.navigation.SystemBackButtonHandler


data class TermsAndConditionsItem(val title: String, val content: String)

val termsAndConditionsList = listOf(
    TermsAndConditionsItem("1. Acceptance of Terms:", "By using this chat app, you agree to comply with the following terms of use."),
    TermsAndConditionsItem("2. User Conduct:", "Users are expected to behave responsibly and respectfully while using the chat app. Prohibited actions include..."),
    TermsAndConditionsItem("3. Privacy Policy:", "We respect your privacy and handle your personal information in accordance with our privacy policy. Please review our privacy policy for more details."),
    TermsAndConditionsItem("4. Intellectual Property:", "All intellectual property rights associated with the chat app, including trademarks, logos, and content, are owned by the app developers."),
    TermsAndConditionsItem("5. Prohibited Content:", "Users are not allowed to post or share any content that is illegal, abusive, or violates the rights of others."),
    TermsAndConditionsItem("6. Account Suspension:", "The app administrators reserve the right to suspend or terminate user accounts for violating the terms of use or engaging in inappropriate behavior."),
    TermsAndConditionsItem("7. Limitation of Liability:", "The app developers are not liable for any damages or losses incurred while using the chat app."),
    TermsAndConditionsItem("8. Changes to Terms of Use:", "The app developers may modify or update the terms of use at any time. Users will be notified of any changes."),
    TermsAndConditionsItem("9. Governing Law:", "The terms of use are governed by and interpreted in accordance with the laws of India(In)."),

    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionsScreen() {
    val isAgreed = remember { mutableStateOf(false) }
    val isContactDialogVisible = remember { mutableStateOf(false) }

    val uiColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val uiTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val scaffoldColor = if (isSystemInDarkTheme())  Color(0xff494F55) else Color(0xffececec)


    Scaffold(

        containerColor = uiColor,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        "Terms of Use",
                        fontFamily = AppFonts.fontFamily,
                        color = uiTextColor
                    )
                },
                actions = {
                    IconButton(onClick = {
                        isContactDialogVisible.value = true

                    }) {
                        Icon(
                            imageVector = Icons.Outlined.ContactMail,
                            contentDescription = "Developer Info"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            AppRouter.navigateTo(Screen.SignUpScreen)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = uiTextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = scaffoldColor

                )
            )



        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(uiColor)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .background(uiColor),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(termsAndConditionsList) { item ->
                        Column {
                            Text(
                                text = item.title,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    fontFamily = AppFonts.fontFamily,
                                ),
                                modifier = Modifier.padding(bottom = 4.dp, start = 2.dp)
                            )
                            Text(
                                text = item.content,
                                modifier = Modifier.padding(bottom = 16.dp, start = 6.dp),
                                color = uiTextColor,
                                fontFamily = AppFonts.fontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal

                            )
                        }
                    }
                }


            }
        },

        )
    if (isContactDialogVisible.value) {
        Dialog(
            onDismissRequest = { isContactDialogVisible.value = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
        ) {
            val nameHeading = "Name: "
            val nameValue = "Jasmeet Singh"
            val emailHeading = "Email: "
            val emailValue = "sjasmeet438@gmail.com"
            val context = LocalContext.current

            val nameAnnotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AppFonts.fontFamily,
                        fontSize = 18.sp
                    )
                ) {
                    append(nameHeading)
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        fontFamily = AppFonts.fontFamily,
                        fontSize = 17.sp
                    )
                ) {
                    append(nameValue)
                }

            }
            val emailAnnotatedSting = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AppFonts.fontFamily,
                        fontSize = 18.sp
                    )
                ) {
                    append(emailHeading)
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        fontFamily = AppFonts.fontFamily,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 17.sp
                    )
                ) {
                    pushStringAnnotation(
                        tag = emailValue,
                        annotation = emailValue
                    )
                    append(emailValue)
                }

            }



            Card(
                shape = RoundedCornerShape(25.dp)
            ) {

                Column (
                    Modifier
                        .background(Color.White)
                        .padding(16.dp)) {


                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        CircleAvatar(image = painterResource(id = R.drawable.developer))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Developer Info",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AppFonts.fontFamily,
                                color = Color.Black
                            ),
                        )

                    }


                    ClickableText(
                        text = nameAnnotatedString ,
                        onClick ={_ ->
                            //Nothing to do here
                        },
                        modifier = Modifier
                            .padding(8.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,

                        )
                    ClickableText(
                        text = emailAnnotatedSting,
                        onClick ={offset ->

                            emailAnnotatedSting.getStringAnnotations(offset,offset)
                                .firstOrNull()?.also {span->
                                    if (span.item == emailValue) {
                                        val intent = Intent(Intent.ACTION_SENDTO)
                                        intent.data = Uri.parse("mailto:")
                                        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailValue))
                                        intent.putExtra(Intent.EXTRA_SUBJECT, "Contacting from the app")
                                        intent.putExtra(Intent.EXTRA_TEXT, "Hi Jasmeet...contacting you for ,")
                                        startActivity(context,intent,null)
                                    }
                                }
                        },
                        modifier = Modifier
                            .padding(8.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,

                        )

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { isContactDialogVisible.value = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Close")

                    }

                }

            }



        }

    }

    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignUpScreen)
    }
}
