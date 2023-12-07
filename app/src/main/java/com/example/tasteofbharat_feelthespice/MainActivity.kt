package com.example.tasteofbharat_feelthespice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tasteofbharat_feelthespice.app.TasteOfBharatApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasteOfBharatApp()
        }
    }
}
