package com.example.myapplication.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.drawable.Animatable
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import kotlinx.coroutines.delay
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/** Загрузочный экран. Отображается 1,5 секунды с помощью задержки (delay(1500L))
 * Далее с помощью navController,navigate() запускает страницу LogIn
 *
 * */
@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen(navController: NavHostController) { //navController: NavHostController
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        // portrait mode
        LaunchedEffect(key1 = true) {
            // Customize the delay time
            delay(1500L)
            navController.navigate("onboardingscreen") {
                popUpTo("splashScreen")//удаляет страницу и стека, чтобы не было возможности вернуться к этому экрану
                {
                    inclusive = true
                }
            }
        }
        //Верстка, про каждый компонент и свойство о нем, вы можете почитать в moodle
        val ScreenHeight = LocalConfiguration.current.screenHeightDp.dp
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xFF83A0F8))

                .background(
                    Brush.verticalGradient(colors = listOf(Color(0xFFA1CAFF), Color(0xFF4D9CFF), Color(0xFFA1CAFF)))
                )
                .background(
                    Brush.verticalGradient(colors = listOf(Color(0xFF74C8E4), Color(0xFF73D5BC), Color(0xFF74C8E4)))
                )
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0x0D6269F0), Color(0xA63740F5), Color(0xFF2254F5),
                            Color(0xA63740F5), Color(0x0D6269F0)))),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            /*Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "background of splash screen",
                modifier = Modifier.fillMaxSize()
            )*/
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "splash screen logo",
                modifier = Modifier.size(250.dp)
            )
        }
    }
}

@Composable
fun SplashScreenPreview()
{

}
