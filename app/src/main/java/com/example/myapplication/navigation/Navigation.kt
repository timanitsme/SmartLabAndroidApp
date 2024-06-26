package com.example.myapplication.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.api.RepositoryImpl
import com.example.myapplication.api.RetrofitInstance
import com.example.myapplication.screens.Authorization
import com.example.myapplication.screens.ContentView
import com.example.myapplication.screens.PatientCard
import com.example.myapplication.screens.SplashScreen
import com.example.myapplication.screens.OnBoardingScreen
import com.example.myapplication.screens.PinCode
import com.example.myapplication.viewmodel.ViewModelMain
import kotlinx.coroutines.flow.collectLatest

/*Класс для перемещения по страницам*/
@Composable
fun Navigation(viewModel: ViewModelMain) {
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
    var userEmail by remember { mutableStateOf("") }
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
            Authorization(navController, viewModel){
                email-> userEmail = email
            }
        }
        composable("entercodescreen") {
            ContentView(
                textList = textList,
                requesterList = requesterList,
                navigateToPinCodeScreen = { navController.navigate("pincodescreen") },
                navigateToLogInScreen = {navController.navigate("logInScreen")},
                email = userEmail,
                viewModel = viewModel
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