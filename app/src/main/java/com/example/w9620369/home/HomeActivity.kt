package com.example.w9620369.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.w9620369.screens.HomeScreen

class HomeActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set up your Compose content here, and make sure to observe the LiveData
            // or StateFlow provided by the HomeViewModel in your Composable functions
            HomeScreen()
        }
        homeViewModel.getMenuItems()
    }
}