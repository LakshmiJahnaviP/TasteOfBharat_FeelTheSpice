package com.example.tasteofbharat_feelthespice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tasteofbharat_feelthespice.data.dao.MenuItemDao
import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity

@Database(entities = [MenuItemEntity::class], version = 1, exportSchema = false)
abstract class MenuItemDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: MenuItemDatabase? = null

        fun getDatabase(context: Context): MenuItemDatabase {
            val menuDatabase = INSTANCE
            if (menuDatabase != null) {
                return menuDatabase
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MenuItemDatabase::class.java,
                    "menuItem_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}