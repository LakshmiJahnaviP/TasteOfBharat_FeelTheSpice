package com.example.w9620369.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.w9620369.components.ButtonComponent
import com.example.w9620369.home.HomeViewModel
import com.example.w9620369.navigation.Screen
import com.example.w9620369.navigation.TasteOfBharatRouter
import com.example.w9620369.ui.theme.LightOrange


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderConfirmationScreen() {
    val homeViewModel = viewModel<HomeViewModel>()
    homeViewModel.clearCart()

    var offsetState by remember { mutableStateOf(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightOrange)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 200.dp)
                .offset(y = offsetY.dp)
        ) {
            Text(
                text = "Order Confirmed!",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                ),
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 280.dp)
        ) {
            Text(
                text = "Contact 07896781523 for any queries",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                ),
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))

            ButtonComponent(
                value = "Return to Home",
                onButtonClicked = { TasteOfBharatRouter.navigateTo(Screen.HomeScreen) },
                isEnabled = true
            )
        }
    }
}
