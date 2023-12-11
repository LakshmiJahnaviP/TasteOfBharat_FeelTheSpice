package com.example.tasteofbharat_feelthespice.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MenuItems")
data class MenuItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "desc")
    var desc: String,

    @ColumnInfo(name = "imgUrl")
    var imgUrl: String,

    @ColumnInfo(name = "price")
    var price: Float
)