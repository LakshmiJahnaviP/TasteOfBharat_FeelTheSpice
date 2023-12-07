package com.example.tasteofbharat_feelthespice.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val categories = listOf(
        Category("Veg Starters"),
        Category("Veg Main Courses"),
        Category("Non Veg Starters"),
        Category("Non Veg Main Courses"),
        Category("Desserts"),
        Category("Drinks")
    )

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory = _selectedCategory

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems = _menuItems

    fun selectCategory(category: Category) {
        _selectedCategory.value = category
        loadMenuItems(category)
    }

    private fun loadMenuItems(category: Category) {
        // Load menu items based on the selected category, you can fetch from a data source
        viewModelScope.launch {
            _menuItems.value = when (category.title) {
                "Veg Starters" -> listOf(
                    MenuItem("Paneer 65", "Description", 9.99f),
                    MenuItem("Mushroom", "Description", 8.99f),
                    MenuItem("Babycorn Manchuria", "Description", 8.99f),
                    MenuItem("Veg Manchuria", "Description", 8.99f)
                    )
                "Non Veg Starters" -> listOf(MenuItem("Chicken 65", "Description", 10.99f),
                    MenuItem("Chicken Chilli", "Description", 11.99f))
                // Add cases for other categories
                else -> emptyList()
            }
        }
    }
    fun clearSelectedCategory() {
        _selectedCategory.value = null
        _menuItems.value = emptyList()
    }
}