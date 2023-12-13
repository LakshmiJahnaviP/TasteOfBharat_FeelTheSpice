package com.example.w9620369

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.w9620369.app.TasteOfBharatApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set up your UI structure here
            TasteOfBharatApp()
        }
    }
}



