package com.example.tasteofbharat_feelthespice.data

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tasteofbharat_feelthespice.R
import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity
import com.example.tasteofbharat_feelthespice.data.repository.MenuItemRepository
import com.example.tasteofbharat_feelthespice.database.MenuItemDatabase
import com.example.tasteofbharat_feelthespice.viewmodels.HomeViewModel
import com.example.tasteofbharat_feelthespice.viewmodels.HomeViewModelFactory

class InsertDataActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(MenuItemRepository(MenuItemDatabase.getDatabase(applicationContext)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)

       insertData()
    }

    private fun insertData() {
        val menuItem = MenuItemEntity(id=1,name = "Sample Item",desc= "" , imgUrl = "", price = 10.99f)
        viewModel.insertMenuItem(menuItem)
    }
    }
