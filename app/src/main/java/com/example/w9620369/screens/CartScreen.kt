package com.example.w9620369.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.tasteofbharat_feelthespice.R
import com.example.w9620369.components.AppTopAppBar
import com.example.w9620369.components.ButtonComponent
import com.example.w9620369.home.HomeViewModel
import com.example.w9620369.menu.CartItem
import com.example.w9620369.menu.MenuItem
import com.example.w9620369.navigation.Screen
import com.example.w9620369.navigation.TasteOfBharatRouter

@Composable
fun CartScreen(homeViewModel: HomeViewModel = viewModel()) {

    val cartItems by homeViewModel.cartItems.collectAsState()
    Surface(
        color = colorResource(R.color.light_orange),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.light_orange))
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AppTopAppBar(
                title = "Cart",
                onBackClick = { TasteOfBharatRouter.navigateTo(Screen.HomeScreen) },
                onMenuClick = {
                },
                onCartClick = { TasteOfBharatRouter.navigateTo(Screen.CartScreen) }
            )

            CartContent(homeViewModel)
        }
        Column(){ // Display the total amount
            Text(
                text = "Total: £${calculateTotalAmount(cartItems)}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 540.dp),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Proceed button
            ButtonComponent(
                "Proceed to CheckOut",
                onButtonClicked = {
                    TasteOfBharatRouter.navigateTo(Screen.PaymentScreen)
                },
                isEnabled = cartItems.isNotEmpty()
            )
        }}
    }





@Composable
fun CartContent(homeViewModel: HomeViewModel = viewModel()) {
    val cartItems by homeViewModel.cartItems.collectAsState()
    val onQuantityChanged: (MenuItem, Int) -> Unit = { menuItem, newQuantity ->
        homeViewModel.updateCartItemQuantity(menuItem, newQuantity)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display the cart items
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
            items(cartItems) { cartItem ->
                CartItemRow(
                    cartItem = cartItem,
                    quantity = cartItem.quantity,
                    onQuantityChanged = onQuantityChanged
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun CartItemRow(
    cartItem: CartItem,
    quantity: Int,
    onQuantityChanged: (MenuItem, Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        // Row to contain image and details
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image in the top-left corner
            Image(
                painter = rememberImagePainter(cartItem.menuItem.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Item details
                Text(text = cartItem.menuItem.name, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Price: £${cartItem.menuItem.price}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }

        // Quantity controls in the top-right corner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End // Align items to the end
        ) {
            IconButton(
                onClick = {
                    if (quantity > 0) {
                        onQuantityChanged(cartItem.menuItem, quantity - 1)
                    }
                },
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Text(text = "-", color = Color.White)
            }

            OutlinedTextField(
                value = quantity.toString(),
                onValueChange = {
                    val newQuantity = it.toIntOrNull() ?: quantity
                    onQuantityChanged(cartItem.menuItem, newQuantity)
                },
                label = { Text("Quantity", style = MaterialTheme.typography.bodySmall) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .width(80.dp)
                    .padding(horizontal = 8.dp)
            )

            IconButton(
                onClick = {
                    onQuantityChanged(cartItem.menuItem, quantity + 1)
                },
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Text(text = "+", color = Color.White)
            }
        }
    }
}




fun calculateTotalAmount(cartItems: List<CartItem>): Float {
    return cartItems.sumOf { it.menuItem.price * it.quantity  }.toFloat()
}
