package com.example.w9620369

import android.app.Application

import com.example.w9620369.home.HomeViewModel
import com.google.firebase.FirebaseApp

class TasteOfBharatApp:Application() {


    lateinit var homeViewModel: HomeViewModel



    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)


        homeViewModel = HomeViewModel(this)



    }

}