package com.example.tasteofbharat_feelthespice

import android.app.Application
import com.google.firebase.FirebaseApp

class TasteOfBharatApp:Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}