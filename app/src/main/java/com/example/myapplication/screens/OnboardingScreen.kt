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
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.Year
import java.util.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
//@Preview(showBackground = true)
@Composable
fun OnBoardingScreen(navHost: NavHostController){ // navHost: NavHostController
    val pagerState = rememberPagerState(0, 0F){
        3
    }

    var resource = remember{0}
    var scipper = remember{""}
    var title = remember{""}
    var content = remember{""}
    val coroutineScope = rememberCoroutineScope()
    var functionVariable: () -> Unit = {}


    Box(modifier = Modifier.fillMaxSize())
    {
        HorizontalPager(state = pagerState) {
            Box(modifier = Modifier.fillMaxSize())
            {
                Column (
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()){

                    when(pagerState.currentPage)
                    {
                        0 -> {
                            scipper = "Пропустить"
                            title = "Анализы"
                            content = "Экспресс сбор и получение проб"
                            resource = R.drawable.onboardingpicfirst
                            functionVariable = {coroutineScope.launch {
                                // Call scroll to on pagerState
                                pagerState.scrollToPage(2)
                            }}
                        }
                        1 -> {
                            scipper = "Пропустить"
                            title = "Уведомления"
                            content = "Вы быстро узнаете о результатах"
                            resource = R.drawable.onboardingpicsec
                            functionVariable = {coroutineScope.launch {
                                // Call scroll to on pagerState
                                pagerState.scrollToPage(2)
                            }}
                        }
                        2 -> {
                            scipper = "Завершить"
                            title = "Мониторинг"
                            content = "Наши врачи всегда наблюдают\nза вашими показателями здоровья"
                            resource = R.drawable.onboardingpicthird
                            functionVariable = {navHost.navigate("loginscreen"){
                                popUpTo("onboardingscreen")
                                {
                                    inclusive = true
                                }
                            }}
                        }
                    }


                    TextButton(onClick = functionVariable, modifier = Modifier.padding(start = 30.dp, top =  49.dp)) {
                        Text(scipper,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Left, fontWeight = FontWeight.SemiBold, color = Color(0xFF57A9FF)
                        )
                    }

                    Text(title, modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 200.dp),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, color = Color(0xFF00B712)
                    )
                    Text(content, modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 29.dp),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center, color = Color(0xFF939396)
                    )
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center){
                        repeat(3){
                            CustomIndicator(isSelected = pagerState.currentPage == it)
                        }
                    }
                    Image(painterResource(id = resource), contentDescription = "OnBoardingScreens",
                        modifier = Modifier
                            .size(350.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(top = 150.dp))
                }
            }
        }
    }


}

@Composable
fun CustomIndicator(isSelected: Boolean){
    Box(modifier = Modifier
        .padding(2.dp)
        .background(
            color = if (isSelected) Color(0xFF57A9FF) else Color.White,
            shape = CircleShape
        )
        .size(15.dp)
        .border(2.dp, color = Color(0xFF57A9FF), shape = CircleShape))
}
