package com.example.tasteofbharat_feelthespice

import android.app.Application
import com.example.tasteofbharat_feelthespice.data.dao.MenuItemDao
import com.example.tasteofbharat_feelthespice.data.repository.MenuItemRepository
import com.example.tasteofbharat_feelthespice.database.MenuItemDatabase
import com.example.tasteofbharat_feelthespice.viewmodels.HomeViewModel
import com.google.firebase.FirebaseApp

class TasteOfBharatApp:Application() {

    lateinit var menuDatabase: MenuItemDatabase
   lateinit var menuRepository: MenuItemRepository
    lateinit var homeViewModel: HomeViewModel
    lateinit var menuDao: MenuItemDao


    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        menuDatabase =  MenuItemDatabase.getDatabase(applicationContext)
        menuRepository = MenuItemRepository(menuDatabase)
        menuDao = menuDatabase.menuDao()
        homeViewModel = HomeViewModel(this)



    }

}