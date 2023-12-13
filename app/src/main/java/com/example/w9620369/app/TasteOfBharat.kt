package com.example.w9620369.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.tasteofbharat_feelthespice.R
import com.example.w9620369.navigation.Screen
import com.example.w9620369.navigation.TasteOfBharatRouter
import com.example.w9620369.screens.CartScreen
import com.example.w9620369.screens.HomeScreen
import com.example.w9620369.screens.LoginScreen
import com.example.w9620369.screens.OrderConfirmationScreen
import com.example.w9620369.screens.PaymentScreen
import com.example.w9620369.screens.SignUpScreen
import com.example.w9620369.screens.TasteOfBharatSplashScreen


@Composable
fun TasteOfBharatApp(){
    Surface(modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.light_orange)

    )
    {
        Crossfade(targetState = TasteOfBharatRouter.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.LoginScreen-> {
                    LoginScreen()
                }
                is Screen.HomeScreen-> {
                    HomeScreen()
                }
                is Screen.TOBSplashScreen-> {
                    TasteOfBharatSplashScreen()
                }
                is Screen.CartScreen-> {
                    CartScreen()

                }
                is Screen.PaymentScreen->{
                    PaymentScreen()
                }
                is Screen.OrderConfirmedScreen->{
                    OrderConfirmationScreen()
                }

            }
        }
    }

}
