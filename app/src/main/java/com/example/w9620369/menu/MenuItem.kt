package com.example.w9620369.menu

data class MenuItem(
    var id: Int,
    var name: String,
    var desc: String,
    var imageUrl: String,
    var price: Double,
    val quantity: Int
)