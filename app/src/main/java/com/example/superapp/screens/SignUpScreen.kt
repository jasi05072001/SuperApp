package com.example.superapp.screens

import android.graphics.drawable.Icon
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superapp.R
import com.example.superapp.components.AppFonts
import com.example.superapp.components.CheckBoxComponent
import com.example.superapp.components.HeadingText
import com.example.superapp.components.MyTextField
import com.example.superapp.components.NormalText
import com.example.superapp.components.PassWordField
import com.example.superapp.rememberImeState
import com.example.superapp.ui.theme.BlueGray

@Composable
fun SignUpScreen() {
    val uiColors = if (isSystemInDarkTheme()) Color.Black else Color.White

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    Surface {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            val  uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            TopSection(uiColor)
            Spacer(modifier = Modifier.height( 10.dp))
            BottomSection()



        }
    }
    LaunchedEffect(key1 =imeState.value){
        if(imeState.value){
            scrollState.animateScrollTo(
                scrollState.maxValue,
                animationSpec = tween(1000, easing = EaseInOut)
            )
        }
    }
}


@Composable
private fun TopSection(uiColor: Color) {
    val height = LocalConfiguration.current.screenHeightDp.dp
    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(height.div(2.5f)),

            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.size(4.dp))
            NormalText(
                value = stringResource(id = R.string.hello)
            )
            HeadingText(
                value = stringResource(id = R.string.create_account)
            )

        }


    }
}


@Composable
private fun BottomSection() {

    Column(Modifier.padding(horizontal = 15.dp)) {
        MyTextField(labelValue = stringResource(id = R.string.name), icon = Icons.Outlined.PersonOutline )
        Spacer(modifier = Modifier.height(5.dp))
        MyTextField(labelValue = stringResource(id = R.string.email), icon = Icons.Outlined.Email,isEmail = true )
        Spacer(modifier = Modifier.height(5.dp))
        PassWordField(labelValue = stringResource(id = R.string.password), icon =Icons.Outlined.Lock )
        Spacer(modifier = Modifier.height(5.dp))
        CheckBoxComponent(value = "By continuing you accept our Privacy Policy and Terms of Use")
    }

}

@Preview(device = Devices.PIXEL_XL)
@Composable
fun DefaultPreview() {
    SignUpScreen()
}