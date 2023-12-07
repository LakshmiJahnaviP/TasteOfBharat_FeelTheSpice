package com.example.tasteofbharat_feelthespice.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.tasteofbharat_feelthespice.R
import com.example.tasteofbharat_feelthespice.navigation.Screen
import com.example.tasteofbharat_feelthespice.navigation.TasteOfBharatRouter
import com.example.tasteofbharat_feelthespice.screens.HomeScreen
import com.example.tasteofbharat_feelthespice.screens.LoginScreen
import com.example.tasteofbharat_feelthespice.screens.SignUpScreen

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

            }
        }
    }


}
