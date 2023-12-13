package com.example.w9620369.menu

data class Order(
    val orderId: String,
    val totalAmount: Double,
    val items: List<OrderItem>
)

data class OrderItem(
    val menuItemId: Int,  // Assuming you have some kind of identifier for menu items
    val quantity: Int
)
