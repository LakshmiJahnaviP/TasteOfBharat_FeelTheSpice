package com.example.tasteofbharat_feelthespice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tasteofbharat_feelthespice.TasteOfBharatApp
import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity
import com.example.tasteofbharat_feelthespice.data.repository.MenuItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _allMenuItems = MutableStateFlow<List<MenuItemEntity>>(emptyList())
    val allMenuItems: StateFlow<List<MenuItemEntity>> = _allMenuItems.asStateFlow()

    // Cart state
    private val _cartItems = MutableStateFlow<List<MenuItemEntity>>(emptyList())
    val cartItems: StateFlow<List<MenuItemEntity>> = _cartItems.asStateFlow()

    init {
        viewModelScope.launch {
            (application as? TasteOfBharatApp)?.menuRepository?.getMenuItems()?.collect {
                _allMenuItems.value = it
            }
        }
    }

    fun insertMenuItem(menuItem: MenuItemEntity) {
        viewModelScope.launch {
            _allMenuItems.value = _allMenuItems.value + menuItem

            }
        }


    fun addToCart(menuItem: MenuItemEntity) {
        // Add the selected menu item to the cart
        _cartItems.value = _cartItems.value + menuItem
    }

    fun clearCart() {
        // Clear all items from the cart
        _cartItems.value = emptyList()
    }

    fun observeCart(): Flow<List<MenuItemEntity>> {
        // Observe changes in the cart state
        return _cartItems
    }
}
class HomeViewModelFactory(private val repository: MenuItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(application = Application()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}