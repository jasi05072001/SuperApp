package com.example.superapp.components

import android.widget.CheckBox
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
fun NormalText(value:String) {

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
fun HeadingText(value:String) {

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
fun MyTextField(labelValue :String, icon: ImageVector,isEmail :Boolean = false) {

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
fun PassWordField(labelValue :String, icon: ImageVector) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
        value = password.value,
        onValueChange = { password.value = it },
        singleLine = true,
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
        keyboardActions = KeyboardActions(

        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
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
fun CheckBoxComponent(value :String) {

    val checkBoxState = remember {
        mutableStateOf(false)
    }
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Yellow
    val uiColor2 = if (isSystemInDarkTheme()) Color.Blue else Color.DarkGray

    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked  = checkBoxState.value,
            onCheckedChange = { checkBoxState.value = it },
            colors = CheckboxDefaults.colors(
                checkmarkColor = uiColor,
                checkedColor = uiColor2,
            )
        )
        NormalText(value =value)

    }

}