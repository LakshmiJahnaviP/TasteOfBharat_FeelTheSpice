package com.example.tasteofbharat_feelthespice.screens


import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.example.tasteofbharat_feelthespice.R
import com.example.tasteofbharat_feelthespice.navigation.Screen
import com.example.tasteofbharat_feelthespice.navigation.TasteOfBharatRouter
import kotlinx.coroutines.delay


@Composable
fun TasteOfBharatSplashScreen() {

    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 2500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        delay(3000L)
        Log.d("SplashScreen", "Delay completed")
        
        TasteOfBharatRouter.navigateTo(Screen.SignUpScreen)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.taste_of_bharat),
            contentDescription = "Splash Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .scale(scale.value)
        )
    }

}