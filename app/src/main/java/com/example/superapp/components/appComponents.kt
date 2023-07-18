package com.example.superapp.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.superapp.R
import com.example.superapp.data.NavigationItem
import com.example.superapp.ui.theme.focusedTextFieldText
import com.example.superapp.ui.theme.unfocusedTextFieldText


object AppFonts{
    val fontFamily = FontFamily(
        Font(R.font.roboto_seriff)
    )

}

@Composable
fun NormalTextComponent(value:String) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Text(
        text = value,
        color = uiColor,
        fontFamily = AppFonts.fontFamily,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center

    )

}
@Composable
fun HeadingTextComponent(value:String) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Text(
        text = value,
        color = uiColor,
        fontFamily = AppFonts.fontFamily,
        modifier = Modifier
            .fillMaxWidth(),
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    labelValue: String,
    icon: ImageVector,
    isEmail: Boolean = false,
    onTextSelected: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        singleLine = true,
        maxLines = 1,
        label = {
            Text(
                text = labelValue,
                color = uiColor,
                fontFamily =AppFonts.fontFamily,
            )
        },
        leadingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = uiColor,
                )
                Spacer(modifier = Modifier.width(1.dp))
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            focusedBorderColor = MaterialTheme.colorScheme.focusedTextFieldText,
            focusedTrailingIconColor = MaterialTheme.colorScheme.focusedTextFieldText,
            unfocusedLabelColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            cursorColor = MaterialTheme.colorScheme.focusedTextFieldText,
        ),
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isEmail) KeyboardType.Email else KeyboardType.Text,
            imeAction = imeAction,
            autoCorrect = false,

            )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldComponent(labelValue :String, icon: ImageVector, onTextSelected: (String) -> Unit) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        singleLine = true,
        maxLines = 1,
        label = {
            Text(
                text = labelValue,
                color = uiColor,
                fontFamily =AppFonts.fontFamily,
            )
        },
        leadingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = uiColor,
                )
                Spacer(modifier = Modifier.width(1.dp))
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            focusedBorderColor = MaterialTheme.colorScheme.focusedTextFieldText,
            focusedTrailingIconColor = MaterialTheme.colorScheme.focusedTextFieldText,
            unfocusedLabelColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            cursorColor = MaterialTheme.colorScheme.focusedTextFieldText,
        ),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        trailingIcon = {

            val iconImage =
                if (passwordVisible.value)
                    Icons.Outlined.Visibility
                else
                    Icons.Outlined.VisibilityOff

            val description = if (passwordVisible.value)
                stringResource(R.string.hide_password)
            else
                stringResource(R.string.show_password)

            IconButton(
                onClick ={
                    passwordVisible.value = !passwordVisible.value}
            ) {
                Icon(
                    imageVector = iconImage,
                    contentDescription = description,
                    tint = uiColor,
                )
            }
        },
        visualTransformation =
        if (passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation()

    )

}

@Composable
fun CheckBoxComponent(
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {

    val checkBoxState = remember {
        mutableStateOf(false)
    }
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Yellow
    val uiColor2 = if (isSystemInDarkTheme()) Color.Blue else Color.DarkGray

    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Checkbox(
            checked  = checkBoxState.value,
            onCheckedChange = {
                checkBoxState.value = it
                onCheckedChange(it)
                Log.d("Checked", "CheckBoxComponent: $it")
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = uiColor,
                checkedColor = uiColor2,
            ),
            modifier = Modifier
                .size(20.dp)


        )
        Spacer(modifier = Modifier.width(8.dp))
        ClickableTextComponent(onTextSelected)

    }

}

@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit ) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsOfServiceText = "Terms of Service"

    val annotatedSting = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = uiColor,
                fontWeight = FontWeight.Normal,
                fontFamily = AppFonts.fontFamily
            )
        ) {
            append(initialText)
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFonts.fontFamily,
                textDecoration = TextDecoration.Underline
            )
        ) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        withStyle(
            style = SpanStyle(
                color = uiColor,
                fontWeight = FontWeight.Normal,
                fontFamily = AppFonts.fontFamily
            )
        ) {
            append(andText)
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFonts.fontFamily,
                textDecoration = TextDecoration.Underline
            )
        ) {
            pushStringAnnotation(tag = termsOfServiceText, annotation = termsOfServiceText)
            append(termsOfServiceText)
        }

    }

    ClickableText(
        text = annotatedSting ,
        onClick ={offset ->

            annotatedSting.getStringAnnotations(offset,offset)
                .firstOrNull()?.also {span->
                    if ((span.item == privacyPolicyText) || (span.item == termsOfServiceText)) {
                        onTextSelected(span.item)

                    }
                }
        },
        modifier = Modifier
            .padding(8.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )

}

@Composable
fun CircleAvatar(
    image: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = 30.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clip(CircleShape)
    ) {
        Image(
            painter = image,
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun ButtonComponent(
    value: String,
    onClick: () -> Unit,
    isEnabled : Boolean = false
) {
    val buttonColor =
        if (isSystemInDarkTheme())
            Color(0xff334155)
        else
            Color(0xff000113)

    val textColor = Color.White

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        enabled = isEnabled,
    ) {
        Text(
            text = value,
            fontFamily = AppFonts.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }

}

@Composable
fun DividerTextComponent() {

    val dividerColor =
        if (isSystemInDarkTheme())
            Color(0xffffffff)
        else
            Color(0xff000113)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            color = dividerColor,
            thickness = 0.7.dp
        )

        Text(
            text = stringResource(R.string.or),
            color = dividerColor,
            modifier = Modifier
                .padding(8.dp),
            fontFamily = AppFonts.fontFamily,
        )
        Divider(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            color = dividerColor,
            thickness = 0.7.dp
        )

    }
}

@Composable
fun OtherLoginOptionsComponent( image: Painter,onClick: () -> Unit,contentDescription: String? = null) {

    val uiColor =
        if (isSystemInDarkTheme())
            Color(0xff334155)
        else
            Color(0xffffffff)

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(50.dp)
        ,
        colors = CardDefaults.cardColors(containerColor =  uiColor)

    ) {
        Box(Modifier.clickable(onClick = onClick)) {
            Image(
                painter = image,
                contentDescription = contentDescription,
                Modifier
                    .padding(10.dp)
                    .size(40.dp)

            )
        }
    }
}

@Composable
fun AlreadyHaveAccountComponent(
    startValue:String,
    clickableValue:String,
    onClick: () -> Unit
) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = uiColor,
                fontWeight = FontWeight.Normal,
                fontFamily = AppFonts.fontFamily,
                fontSize = 16.sp
            )
        ) {
            append(startValue)
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = AppFonts.fontFamily,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )
        ) {
            pushStringAnnotation(tag = clickableValue, annotation = clickableValue)
            append(clickableValue)
        }

    }

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(offset, offset)
                    .firstOrNull()?.also { span ->
                        if (span.item == clickableValue) {
                            onClick()
                        }
                    }
            },

            )
    }
}

@Composable
fun UnderLinedClickableTextComponent(value: String,onClick: () -> Unit) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End,modifier = Modifier.fillMaxWidth()) {
        Text(
            text = value,
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick),
            color = uiColor,
            fontFamily = AppFonts.fontFamily,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun LoaderComponent() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.loader2)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = false

    )
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds

            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    title: String,
    logoutButtonClick: () -> Unit,
    navigationIconClick: () -> Unit,

    ) {
    val toolBarColor = if (isSystemInDarkTheme()) Color(0xff1E293B) else Color(0xffBFDBFE)
    val titleColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val logoutDialog = remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    val dropDownMenuColor = if (isSystemInDarkTheme()) Color(0xB3000000) else Color(0xB3FFFFFF)

    TopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = AppFonts.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = titleColor
            )
        },
        navigationIcon = {
            IconButton(onClick = navigationIconClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    tint = titleColor
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = toolBarColor),
        actions = {

            Row {

                IconButton(onClick = { logoutDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Logout",
                        tint = titleColor
                    )
                }
                IconButton(onClick = { expanded.value = true }) {

                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = titleColor
                    )

                }


            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.background(dropDownMenuColor)

            ) {
                DropdownMenuItem(onClick = { /* Handle menu item 1 click */ }) {
                    Text(
                        "Update Profile",
                        color = titleColor,
                        fontFamily = AppFonts.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp

                    )
                }
                DropdownMenuItem(onClick = { /* Handle menu item 2 click */ }) {
                    Text(
                        "Chat with bot",
                        color = titleColor,
                        fontFamily = AppFonts.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
                DropdownMenuItem(onClick = { /* Handle menu item 3 click */ }) {
                    Text(
                        "Logout",
                        color = titleColor,
                        fontFamily = AppFonts.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    )

    if (logoutDialog.value) {
        LogoutConfirmationDialog(
            onDismissRequest = { logoutDialog.value = false },
            onLogoutConfirmed = { logoutButtonClick.invoke() }
        )
    }
}

@Composable
fun LogoutConfirmationDialog(
    onDismissRequest: () -> Unit,
    onLogoutConfirmed: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(10.dp)) {
            Column(
                Modifier
                    .background(Color.White)
                    .wrapContentSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Logout",
                    fontSize = 19.sp,
                    fontFamily = AppFonts.fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Are you sure you want to logout? Logging out will end your current session.",
                    fontSize = 16.sp,
                    fontFamily = AppFonts.fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(
                        onClick = onDismissRequest
                    ) {
                        Text(
                            text = "Cancel",
                            color = Color.Blue,
                            fontFamily = AppFonts.fontFamily,
                            fontSize = 16.sp
                        )
                    }
                    TextButton(
                        onClick = {
                            onLogoutConfirmed()
                            onDismissRequest()
                        }
                    ) {
                        Text(
                            text = "Logout",
                            color = Color.Red,
                            fontFamily = AppFonts.fontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun NavigationDrawerHeader(value: String?) {
    val containerColor = if (isSystemInDarkTheme()) Color.Cyan else Color(0xff14213d)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 180.dp, max = 190.dp)
            .background(containerColor),
        contentAlignment = Alignment.Center
    ) {

        val uiColor = if (isSystemInDarkTheme()) Color.Black else Color.White

        Text(
            text = value ?: "Welcome to Jetpack Compose",
            color = uiColor,
            fontFamily = AppFonts.fontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center


        )

    }

}

@Composable
fun NavigationDrawerBody(
    navigationDrawerItems: List<NavigationItem>,
    onNavigationItemClick: (NavigationItem) -> Unit
) {

    val dividerColor =
        if (isSystemInDarkTheme())
            Color(0xffffffff)
        else
            Color(0xff000113)

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(navigationDrawerItems) {
            NavigationItemRow(item = it, onNavigationItemClick = { item ->
                onNavigationItemClick.invoke(item)
            })
            Divider(
                color = dividerColor, thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

        }
    }
}

@Composable
fun NavigationItemRow(item: NavigationItem, onNavigationItemClick: (NavigationItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable {
                onNavigationItemClick.invoke(item)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.description
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            fontFamily = AppFonts.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

    }

}



