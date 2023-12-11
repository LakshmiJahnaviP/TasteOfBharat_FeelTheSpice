package com.example.tasteofbharat_feelthespice.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController

sealed class Screen{
    object SignUpScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
    object TOBSplashScreen: Screen()
    object CartScreen: Screen()
    object PaymentScreen: Screen()
    object OrderConfirmedScreen: Screen()
}
object TasteOfBharatRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.TOBSplashScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }

    var navController: NavController? = null

    fun navigateTo(route: String) {
        navController?.navigate(route)
    }
}