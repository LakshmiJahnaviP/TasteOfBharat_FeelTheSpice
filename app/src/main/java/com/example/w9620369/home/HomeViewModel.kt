package com.example.w9620369.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.w9620369.menu.CartItem
import com.example.w9620369.menu.MenuItem
import com.example.w9620369.menu.Order
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val menuItemsCollection = FirebaseFirestore.getInstance().collection("menuItems")

    //menu state
    private val _allMenuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val allMenuItems: StateFlow<List<MenuItem>> = _allMenuItems.asStateFlow()

    // Cart state
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    //cart count state
    private val _cartItemCount = MutableStateFlow(0)
    val cartItemCount: StateFlow<Int> = _cartItemCount.asStateFlow()

    init {
        getMenuItems()
    }

    fun getMenuItems() {
        menuItemsCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val itemsList = mutableListOf<MenuItem>()

                for (document in querySnapshot) {
                    try {
                        val desc = document.getString("desc") ?: ""
                        val id = document.getLong("id")?.toInt() ?: 0
                        val imageUrl = document.getString("imageUrl") ?: ""
                        val name = document.getString("name") ?: ""
                        val price = document.getDouble("price") ?: 0.0
                        val quantity = document.getLong("quantity")?.toInt() ?: 0

                        val menuItem = MenuItem(
                            desc = desc,
                            id = id,
                            imageUrl = imageUrl,
                            name = name,
                            price = price,
                            quantity = quantity
                        )

                        itemsList.add(menuItem)
                    } catch (e: Exception) {
                        Log.e("Firestore", "Error parsing document: ${document.id}", e)
                    }
                }

                _allMenuItems.value = itemsList
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents: ", exception)
            }
    }

    fun addToCart(menuItem: MenuItem, quantity: Int) {
        val existingCartItem = _cartItems.value.find { it.menuItem.id == menuItem.id }

        if (existingCartItem != null) {
            // Item is already in the cart, update the quantity
            updateCartItemQuantity(menuItem, existingCartItem.quantity + quantity)
        } else {
            // Item is not in the cart, add a new CartItem
            val newCartItem = CartItem(menuItem, quantity = quantity)
            _cartItems.value = _cartItems.value + newCartItem
        }
        updateCartCount()
        Log.d("Cart", "Adding to cart: ${menuItem.name}, Quantity: $quantity")

    }

    fun updateCartItemQuantity(menuItem: MenuItem, newQuantity: Int) {
        val updatedCartItems = _cartItems.value.map { cartItem ->
            if (cartItem.menuItem.id == menuItem.id) {
                cartItem.copy(quantity = newQuantity)
            } else {
                cartItem
            }
        }

        _cartItems.value = updatedCartItems
    }

    private fun updateCartCount() {
        _cartItemCount.value = _cartItems.value.sumOf { it.quantity }
    }

    private val quantities = mutableMapOf<MenuItem, Int>()

    private fun saveOrder(order: Order) {
        // Implement logic to save the order to the database
    }

    // Generate a unique order ID (you might want to use a more robust approach)
    private fun generateOrderId(): String {
        return UUID.randomUUID().toString()
    }


    fun clearCart() {
        _cartItems.value = emptyList()
        _cartItemCount.value = 0
        // You may also want to reset the quantities map if needed
        quantities.clear()
    }

}