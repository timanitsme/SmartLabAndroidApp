package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.RoundCheckBox


@Composable
fun LogIn(navHost: NavHostController) {
    var email: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize(1f)) {
        Text(
            text = "Log In", fontSize = 40.sp, color = Color(0xFF1D68FF),
            modifier = Modifier.padding(top = 30.dp, bottom = 50.dp, start = 10.dp)
        )
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomEmail(search = email, onValueChange = {
            })
            CustomPassword(search = password, onValueChange = {
            })
            /*TextField(
                modifier = Modifier.padding(bottom = 10.dp),
                value = email.value,

                onValueChange = { newText -> email.value = newText })
            TextField(
                value = password.value,
                onValueChange = { newText -> password.value = newText })*/
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp,bottom = 15.dp, end = 10.dp), textAlign = TextAlign.End,
            text = "Forget Password?", fontSize = 14.sp, color = Color(0xFF1D68FF)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            var roundCheckBoxState by remember { mutableStateOf(false) }
            RoundCheckBox(
                modifier = Modifier.width(60.dp),
                isChecked = roundCheckBoxState,
                onClick = { roundCheckBoxState = !roundCheckBoxState },
                enabled = true
            )
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0XFFE8EFFF),
                    checkmarkColor = Color(0XFF578FFF)
                )
            )
            Text(text = "Remember Password", fontSize = 14.sp, color = Color(0xFF1D68FF))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFFE8EFFF),
                    contentColor = Color(0xFF1D68FF)
                )
            ) {
                Text(text = "Login")
            }
            Text(
                text = "OR", fontSize = 14.sp, color = Color(0xFF1D68FF),
                modifier = Modifier.padding(vertical = 15.dp)
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFFE8EFFF),
                        contentColor = Color(0xFF1D68FF)
                    )
                ) {
                    Text(text = "GOOGLE")
                }
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFFE8EFFF),
                        contentColor = Color(0xFF1D68FF)
                    )
                ) {
                    Text(text = "FACEBOOK")
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 0.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        )
        {
            Text(
                text = "Dont’ have an account? Let’s Register",
                fontSize = 16.sp,
                color = Color(0xFF1D68FF)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEmail(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0XFFE8EFFF))

    ) {
        TextField(
            value = search,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0XFFE8EFFF),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF),
            ),
            modifier = Modifier.background(Color(0XFFF5F5F9)),
            placeholder = {
                Text(
                    text = "Enter Email / Phone Number",
                    color = Color(0XFF578FFF)
                )
            }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPassword(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0XFFE8EFFF))

    ) {
        TextField(
            value = search,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0XFFE8EFFF),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF),
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, contentDescription = "",
                    tint = Color(0XFF578FFF)
                )
            },
            modifier = Modifier.background(Color(0XFFF5F5F9)),
            placeholder = {
                Text(
                    text = "Password",
                    color = Color(0XFF578FFF)
                )
            }
        )

    }
}


@Preview(showBackground= true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Authorization(navHost: NavHostController) // navHost: NavHostController
{
    Column (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()){
        Text("\uD83D\uDC4B   Добро пожаловать!",
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 20.dp, top =  50.dp,
                bottom = 25.dp, end = 20.dp),
            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
        )
        Text("Войдите, чтобы пользоваться функциями приложения",
            fontSize = 18.sp, modifier = Modifier.padding(start = 20.dp)
        )

        //Ввод email

        var textState by remember { mutableStateOf("") }
        val maxLength = 30
        val lightBlue = Color(0xffd8e6ff)
        val blue = Color(0xff76a9ff)
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
            .padding(horizontal = 20.dp),
            value = textState,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0XFFE8EFFF),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF)),
            onValueChange = {
                if (it.length <= maxLength) textState = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {Text("example@mail.ru", color = Color(0xFF939396))},
            trailingIcon = {
                if (textState.isNotEmpty()) {
                    IconButton(onClick = { textState = "" }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            })

        /*Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            ) { }*/

    }
}