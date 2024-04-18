package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
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
import com.example.myapplication.screens.EnterEmailCode
/*Класс для перемещения по страницам*/
@Composable
fun Navigation() {
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
        composable("entercodescreen")
        {
            EnterEmailCode(navController)
        }

    }
}