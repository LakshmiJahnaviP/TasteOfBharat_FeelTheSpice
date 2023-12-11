package com.example.tasteofbharat_feelthespice

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tasteofbharat_feelthespice.app.TasteOfBharatApp
import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity
import com.example.tasteofbharat_feelthespice.data.repository.MenuItemRepository
import com.example.tasteofbharat_feelthespice.database.MenuItemDatabase
import com.example.tasteofbharat_feelthespice.viewmodels.HomeViewModel
import com.example.tasteofbharat_feelthespice.viewmodels.HomeViewModelFactory
import com.google.firebase.BuildConfig
import timber.log.Timber

class InsertDataActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(MenuItemRepository(MenuItemDatabase.getDatabase(applicationContext)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setContent {

            TasteOfBharatApp()

        }

       insertData()
    }


    fun insertData() {

        val menuItem = MenuItemEntity(id=1,name = "Sample Item",desc= "" , imgUrl = "", price = 10.99f)
        viewModel.insertMenuItem(menuItem)

    }
}
