package com.example.tasteofbharat_feelthespice.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tasteofbharat_feelthespice.components.AppTopAppBar
import com.example.tasteofbharat_feelthespice.components.ButtonComponent
import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity
import com.example.tasteofbharat_feelthespice.navigation.Screen
import com.example.tasteofbharat_feelthespice.navigation.TasteOfBharatRouter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen() {
    val cartItems = remember { mutableStateListOf<MenuItemEntity>() }

    Scaffold(
        topBar = {
            AppTopAppBar(
                title = "Cart",
                onBackClick = { TasteOfBharatRouter.navigateTo(Screen.HomeScreen) },
                onMenuClick = {
                },
                onCartClick = {TasteOfBharatRouter.navigateTo(Screen.CartScreen)}
            )

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Display the cart items
            LazyColumn {
                items(cartItems) { menuItem ->
                    CartItemRow(menuItem = menuItem)
                }
            }

            // Display the total amount
            Text(
                text = "Total: $${calculateTotalAmount(cartItems)}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.End
            )

            // Spacer to push the button to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // Proceed button
            ButtonComponent("Proceed to CheckOut",
                onButtonClicked = {
                    TasteOfBharatRouter.navigateTo(Screen.PaymentScreen)
            },isEnabled = true)
        }
    }
}


@Composable
fun CartItemRow(menuItem: MenuItemEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = menuItem.name ?: "", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Price: $${menuItem.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

fun calculateTotalAmount(cartItems: List<MenuItemEntity>): Float {
    return cartItems.sumByDouble { it.price.toDouble() }.toFloat()
}
