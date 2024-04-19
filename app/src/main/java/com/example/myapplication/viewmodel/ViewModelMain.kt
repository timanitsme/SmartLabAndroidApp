package com.example.myapplication.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.myapplication.model.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelMain(private val repository: Repository): ViewModel() {
    private val _rickAndMorty = MutableStateFlow<List<Result>>(emptyList())
    val rickAndMorty = _rickAndMorty.asStateFlow()
    private val _signInResponseMessage = MutableStateFlow<String>("")
    val signInResponseMessage = _signInResponseMessage.asStateFlow()
    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            //Далее мы с помощью ранее созданнхы метод, получаем данные и обробатываем их
            repository.getCharacter().collect { result ->
                when(result) {
                    is com.example.myapplication.api.Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is com.example.myapplication.api.Result.Success -> {
                        result.data?.let { character ->
                            _rickAndMorty.update { character.results }
                        }
                    }
                }
            }
        }
    }
    fun sendCodeToEmail(email:String)
    {
        viewModelScope.launch {
            repository.sendCodeEmail(email).collect{
                when(it) {
                    is com.example.myapplication.api.Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is com.example.myapplication.api.Result.Success -> {
                        _showErrorToastChannel.send(false)
                    }
                }
            }
        }
    }
    fun signInWithCode(email:String, code: String)
    {
        viewModelScope.launch {
            repository.signIn(email, code).collect{
                result ->
                when(result) {
                    is com.example.myapplication.api.Result.Error -> {
                        _showErrorToastChannel.send(true)
                        _signInResponseMessage.value = result.message.toString()
                    }
                    is com.example.myapplication.api.Result.Success -> {
                        _showErrorToastChannel.send(false)
                        _signInResponseMessage.value = "success"
                    }
                }
            }
        }
    }


}