package com.example.tasteofbharat_feelthespice.data.repository

import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity
import com.example.tasteofbharat_feelthespice.database.MenuItemDatabase
import kotlinx.coroutines.flow.Flow

class MenuItemRepository(private val menuDatabase: MenuItemDatabase) {
    fun getMenuItems(): Flow<List<MenuItemEntity>> {
        return menuDatabase.menuDao().getAllMenuItems()
    }
    suspend fun addMenuItem(item: MenuItemEntity) {
        menuDatabase.menuDao().insert(item)
    }

    suspend fun updateMenuItem(item: MenuItemEntity) {
        menuDatabase.menuDao().update(item)
    }

    suspend fun deleteMenuItem(item: MenuItemEntity) {
        menuDatabase.menuDao().delete(item)
    }

    suspend fun deleteAllMenuItems() {
        menuDatabase.menuDao().deleteAllRecord()
    }
}