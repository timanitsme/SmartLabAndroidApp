package com.example.myapplication.api



/** Класс sealed в Kotlin является одним из встроенных классов, которые можно использовать для предотвращения
наследования пользователями класса. Он может быть запечатан с помощью ключевого слова sealed.
Компилятор немедленно получает информацию о том, что класс запечатан, если вы используете и объявляете
его с ключевым словом sealed. В результате класс не может быть расширен таким образом дополнительными дочерними классами.*/


/**
 <T> (не обязательно буква T) -
 Обобщённые типы (generics) в Kotlin позволяют выполнять контроль типов в приложении, когда точный тип неизвестен.
 */
//Пользовательский метод для обработки данных
sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(data: T? = null,message: String?) : Result<T>(data,message)
}