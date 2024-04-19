package com.example.myapplication.screens

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.RoundCheckBox
import com.example.myapplication.navigation.Navigation
import com.example.myapplication.viewmodel.ViewModelMain

//@Preview(showBackground= true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Authorization(navController: NavController, viewModel: ViewModelMain, onContinueClicked: (String) -> Unit) // navHost: NavHostController
{
    Column (
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
        Text("\uD83D\uDC4B   Добро пожаловать!",
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 20.dp, top =  100.dp,
                bottom = 25.dp, end = 20.dp),
            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
        )
        Text("Войдите, чтобы пользоваться функциями приложения",
            fontSize = 18.sp, modifier = Modifier.padding(start = 20.dp)
        )

        //Ввод email

        var email by remember { mutableStateOf("") }
        val maxLength = 30
        val emailpattern = Regex("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")
        var ColorOfButton by remember { mutableStateOf(0xFFC9D4FB) }
        Text(
            text = "Введите E-mail",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, top = 50.dp, start = 20.dp),
            textAlign = TextAlign.Left,
            color = Color(0xFF7E7E9A)
        )

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .shadow(elevation = 41.dp, spotColor = Color.Black, shape = RoundedCornerShape(8.dp)), //переделать
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F9),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF)),
            onValueChange = {
                if (it.length <= maxLength) email = it
                if (email.isNotEmpty() and emailpattern.matches(email))
                {
                    ColorOfButton = 0xFF1A6FEE
                }
                else
                {
                    ColorOfButton = 0xFFC9D4FB
                }
            },

            singleLine = true,
            placeholder = {Text("example@mail.ru", color = Color(0xFF939396))},
            trailingIcon = {
                if (email.isNotEmpty()) {
                    IconButton(onClick = { email = "" }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            })

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .height(50.dp),
            onClick = {
                      if (emailpattern.matches(email))
                      {
                          viewModel.sendCodeToEmail(email)
                          onContinueClicked(email)
                          navController.navigate("entercodescreen")
                      }
            }, shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(ColorOfButton))
        )
        {Text(text = "Далее", fontSize = 16.sp)}

        Text(
            text = "Или войдите с помощью",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, top = 245.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF7E7E9A)
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .height(60.dp),
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background, contentColor = Color.Black),
            border = BorderStroke(2.dp, Color(0xFFEBEBEB))

        ){Text(text = "Войти с Яндекс", fontSize = 16.sp)}
    }

}