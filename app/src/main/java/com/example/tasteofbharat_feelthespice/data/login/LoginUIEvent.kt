package com.example.tasteofbharat_feelthespice.data.login

sealed class LoginUIEvent{

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()
    object LogoutButtonClicked: LoginUIEvent()
}