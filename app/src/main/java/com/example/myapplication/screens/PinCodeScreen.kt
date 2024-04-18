package com.example.myapplication.screens

import android.service.autofill.DateTransformation
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
import androidx.navigation.NavHostController
import com.example.myapplication.RoundCheckBox
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import java.time.Year
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PinCode(){ // navHost: NavHostController
    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column (
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Text(
            text = "Пропустить",
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 84.dp, end = 20.dp)
                .align(Alignment.End),
            textAlign = TextAlign.End,
            color = Color(0XFF1A6FEE))


        Text(
            "Создайте пароль",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(
                    top = 40.dp
                )
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold)
        Text("Для защиты ваших персональных данных",
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            color = Color(0xFF939396))
        NumbersPanel("1", "2", "3")
        NumbersPanel("4", "5", "6")
        NumbersPanel("7", "8", "9")
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 20.dp)){
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .padding(start = 128.dp)
                .size(80.dp),
                shape = CircleShape, contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color(0xFFF5F5F9)
                )
            ) {
                Text("0", fontSize = 24.sp)
            }

        }

    }

    //ComposePinInput()


}
@Composable
fun NumbersPanel(firstnum: String, secnum: String, thirdnum: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp, start = 20.dp)){
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .padding(start = 24.dp)
            .size(80.dp),
            shape = CircleShape, contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color(0xFFF5F5F9)
            )
        ) {
            Text(firstnum, fontSize = 24.sp)
        }
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .padding(start = 24.dp)
            .size(80.dp),
            shape = CircleShape, contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color(0xFFF5F5F9)
            )
        ) {
            Text(secnum, fontSize = 24.sp)
        }
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .padding(start = 24.dp)
            .size(80.dp),
            shape = CircleShape, contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color(0xFFF5F5F9)
            )
        ) {
            Text(thirdnum, fontSize = 24.sp)
        }
        //Image(painter = painterResource(id = R.drawable.deleteicon))

    }
}