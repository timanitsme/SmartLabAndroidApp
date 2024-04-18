package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.Authorization
import com.example.myapplication.screens.ContentView
import com.example.myapplication.screens.LogIn
import com.example.myapplication.screens.PatientCard
import com.example.myapplication.screens.SplashScreen
import com.example.myapplication.screens.OnBoardingScreen
import com.example.myapplication.screens.PinCode
/*Класс для перемещения по страницам*/
@Composable
fun Navigation() {
    val textList = listOf(
        remember{
            mutableStateOf(
            TextFieldValue(text="", selection = TextRange(0))
        )
        },
        remember{
            mutableStateOf(
            TextFieldValue(text="", selection = TextRange(0))
        )
        },
        remember{
            mutableStateOf(
            TextFieldValue(text="", selection = TextRange(0))
        )
        },
        remember{
            mutableStateOf(
            TextFieldValue(text="", selection = TextRange(0))
        )
        }
    )
    val requesterList = listOf(FocusRequester(), FocusRequester(), FocusRequester(), FocusRequester())
    val navController = rememberNavController()
    NavHost(navController = navController,//контроллер реагирующий и отвечающий за перемещения
        startDestination = "splashScreen")
    {
        //Для каждой страницы задается route - для дальнейшего обращения к ней
        //и указывается метод для запуска с переданными параметрами
        composable("splashScreen"){
            SplashScreen(navController)
        }
        composable("logInScreen"){
            Authorization(navController)
        }
        composable("entercodescreen") {
            ContentView(
                textList = textList,
                requesterList = requesterList,
                navigateToPinCodeScreen = { navController.navigate("pincodescreen") }
            )
        }
        composable("patientCardScreen")
        {
            PatientCard(navController)
        }
        composable("onboardingscreen")
        {
            OnBoardingScreen(navController)
        }
        composable("pincodescreen")
        {
            PinCode(navController)
        }

    }
}