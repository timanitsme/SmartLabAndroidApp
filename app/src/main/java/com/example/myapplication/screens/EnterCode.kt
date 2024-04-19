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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import kotlinx.coroutines.delay
import java.time.Year
import java.util.*
import com.example.myapplication.screens.PinCode
import com.example.myapplication.viewmodel.ViewModelMain
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
fun connectInputedCode(textList: List<MutableState<TextFieldValue>>, email: String, viewModel: ViewModelMain, wrongCode: Boolean,
                       onVerifyCode: ((success: Boolean) -> Unit)? = null
){
    var code = ""
    for (text in textList){
        code += text.value.text
    }
    if (code.length == 4){
        viewModel.signInWithCode(email, code)
        verifyCode(code, viewModel, wrongCode, onSuccess = {
            onVerifyCode?.let{it(true)}
        }, onError = {
            onVerifyCode?.let{it(false)}
        })

    }
}

fun verifyCode(code: String, viewModel: ViewModelMain, wrongCode: Boolean, onSuccess: () -> Unit, onError: () -> Unit){
    if(!wrongCode){
        onError()
    }
    else{
        onSuccess()
    }

}

fun nextFocus(textList: List<MutableState<TextFieldValue>>, requesterList: List<FocusRequester>)
{
    for (index in textList.indices) {
        if (textList[index].value.text == ""){
            if (index < textList.size){
                requesterList[index].requestFocus()
                break
            }
        }
    }
}

@Composable
fun InputView(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    focusRequester: FocusRequester
){
    BasicTextField(value = value, onValueChange = onValueChange, modifier = Modifier
        .focusRequester(focusRequester)
        .padding(horizontal = 10.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0xFFF5F5F9))
        .border(1.dp, Color(0xFFEBEBEB), RoundedCornerShape(8.dp))
        .wrapContentSize(),
        maxLines = 1, decorationBox = {
                innerTextField ->
            Box(modifier = Modifier
                .width(50.dp)
                .height(50.dp),
                contentAlignment = Alignment.Center
            ){
                innerTextField()
            }
        }, cursorBrush = SolidColor(Color.Black),
        textStyle = TextStyle(color = Color.Black, fontSize = 20.sp, textAlign = TextAlign.Center
        ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = null))
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContentView(textList: List<MutableState<TextFieldValue>>, requesterList: List<FocusRequester>, navigateToPinCodeScreen: () -> Unit, navigateToLogInScreen: () -> Unit, email: String, viewModel: ViewModelMain){
    val focusManager = LocalFocusManager.current
    val keyboarController = LocalSoftwareKeyboardController.current
    var wrongCode by remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
        viewModel.showErrorToastChannel.collectLatest { show -> wrongCode = show
        }
    }

    Surface(modifier = Modifier
        .fillMaxSize()){
        Column (
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
            Button(onClick = {navigateToLogInScreen()}, modifier = Modifier
                .padding(start = 26.dp, top = 74.dp)
                .size(32.dp),
                shape = RoundedCornerShape(8.dp), contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color(0xFFF5F5F9)
                ))
            {
                Image(
                    painter = painterResource(id = R.drawable.iconback),
                    contentDescription = "back to email",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Text(
                "Введите код из E-mail",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        top = 132.dp,
                        bottom = 24.dp
                    )
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold)





        }
        Box(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 280.dp)
                .align(Alignment.TopCenter)){
                for (i in textList.indices){
                    InputView(value=textList[i].value, onValueChange={
                        newValue ->
                        if (textList[i].value.text != ""){
                            if (newValue.text == ""){
                                textList[i].value = TextFieldValue(
                                    text = "",
                                    selection = TextRange(0)
                                )
                            }
                            return@InputView
                        }

                        textList[i].value = TextFieldValue(
                            text = newValue.text,
                            selection = TextRange(newValue.text.length)
                        )
                        connectInputedCode(textList, email, viewModel, wrongCode){
                            focusManager.clearFocus()
                            keyboarController?.hide()
                            if(it){
                                Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
                                navigateToPinCodeScreen()
                            }
                            else{
                                Toast.makeText(context, "Неправильный код", Toast.LENGTH_SHORT).show()
                                for (text in textList)
                                {
                                    text.value = TextFieldValue(
                                        text = "",
                                        selection = TextRange(0)
                                    )
                                }
                            }
                        }
                        nextFocus(textList, requesterList)
                    },
                        focusRequester = requesterList[i])
                }
            }

        }
    }
    LaunchedEffect(key1 = null, block = {
        delay(300)
        requesterList[0].requestFocus()
    })
}
