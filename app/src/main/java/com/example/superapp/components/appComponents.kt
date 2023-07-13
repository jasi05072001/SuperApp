package com.example.superapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import com.example.superapp.R
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
fun TextFieldComponent(labelValue :String, icon: ImageVector, isEmail :Boolean = false) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
        value = textValue.value,
        onValueChange = { textValue.value = it },
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
        keyboardActions = KeyboardActions.Default,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isEmail) KeyboardType.Email else KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldComponent(labelValue :String, icon: ImageVector) {

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
        onValueChange = { password.value = it },
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
fun CheckBoxComponent(onTextSelected: (String) -> Unit ) {

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
            onCheckedChange = { checkBoxState.value = it },
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
    isEnabled : Boolean = true
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




