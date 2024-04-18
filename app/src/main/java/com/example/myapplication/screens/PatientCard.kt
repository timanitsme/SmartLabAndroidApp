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
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Year
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun PatientCard(navHost: NavHostController){ //navHost: NavHostController
    var textName by remember { mutableStateOf("") }
    var textPatronymic by remember { mutableStateOf("") }
    var textSurname by remember { mutableStateOf("") }
    var ColorOfButton by remember{ mutableStateOf(0xFFC9D4FB) }
    val context = LocalContext.current
    val genders = arrayOf("Мужской", "Женский")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(genders[0]) }
    // Выбор даты
    val day: Int
    val month: Int
    val year: Int
    val calendar = Calendar.getInstance()
    year = calendar.get((Calendar.YEAR))
    month = calendar.get((Calendar.MONTH))
    day = calendar.get((Calendar.DAY_OF_MONTH))
    var date by remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(context, {_: DatePicker, year: Int, month: Int,
                                                      day: Int -> date = " $day/$month/$year"}, year, month, day)
    Column(modifier= Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Создание карты\nпациента", fontSize = 24.sp,
                modifier = Modifier.padding(top = 76.dp, start = 20.dp), textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = {navHost.navigate("patientCardScreen")},
                modifier = Modifier.padding(top = 84.dp, start = 35.dp,)) {
                Text(
                    text = "Пропустить",
                    fontSize = 15.sp,
                    textAlign = TextAlign.End,
                    color = Color(0XFF1A6FEE)
                )
            }


        }
        Text(
            text = "Без карты пациента вы не сможете заказать анализы.", fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        Text(
            text = "В картах пациентов будут храниться результаты анализов вас и ваших близких",
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 20.dp)
        )
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 32.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
            value = textName,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F9),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF)),
            onValueChange = {
                if (it.length <= 30) textName = it},
            singleLine = true,
            placeholder = { Text("Имя", color = Color(0xFF939396)) })
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 24.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
            value = textPatronymic,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F9),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF)),
            onValueChange = {
                if (it.length <= 30) textPatronymic = it},
            singleLine = true,
            placeholder = { Text("Отчество", color = Color(0xFF939396)) })
        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 24.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
            value = textSurname,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F9),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF)),
            onValueChange = {
                if (it.length <= 30) textSurname = it },
            singleLine = true,
            placeholder = { Text("Фамилия", color = Color(0xFF939396)) })

            /*if ()
            {
                ColorOfButton = 0xFF1A6FEE
            }
            else
            {
                ColorOfButton = 0xFFC9D4FB
            }*/


        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 24.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
            value = date,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF5F5F9),
                focusedIndicatorColor = Color.Black,
                focusedTextColor = Color(0XFF578FFF),
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF578FFF)),
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            placeholder = {Text("Дата рождения", color = Color(0xFF939396))},
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                }
            })
        // Список
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                modifier = Modifier.fillMaxWidth(),
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF5F5F9),
                        focusedIndicatorColor = Color.Black,
                        focusedTextColor = Color(0XFF578FFF),
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(0XFF578FFF)),
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 20.dp, end = 20.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { expanded = false }
                ) {
                    genders.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
        if (textName.isNotEmpty() and textSurname.isNotEmpty() and textPatronymic.isNotEmpty() and selectedText.isNotEmpty() and date.isNotEmpty())
            ColorOfButton = 0xFF1A6FEE
        else
            ColorOfButton = 0xFFC9D4FB
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .height(56.dp)
            .width(335.dp),
            onClick = {
                if(textName.isNotEmpty() and textSurname.isNotEmpty() and textPatronymic.isNotEmpty() and selectedText.isNotEmpty() and date.isNotEmpty())
                    navHost.navigate("pincodescreen")
            }, shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(ColorOfButton))
        ) {Text(text = "Создать", fontSize = 16.sp)}
    }
}



