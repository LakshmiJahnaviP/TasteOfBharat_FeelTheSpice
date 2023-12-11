package com.example.tasteofbharat_feelthespice.screens


import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasteofbharat_feelthespice.R
import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity
import com.example.tasteofbharat_feelthespice.navigation.Screen
import com.example.tasteofbharat_feelthespice.navigation.TasteOfBharatRouter
import com.example.tasteofbharat_feelthespice.ui.theme.LightOrange
import com.example.tasteofbharat_feelthespice.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopAppBar(onBackClick: () -> Unit, onMenuClick: () -> Unit, onCartClick: () -> Unit, cartItemsCount: Int) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    // Taskbar with back, menu, and cart options
    TopAppBar(
        title = {
            Text(
                text = "Taste of Bharat",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                ),
                color = Color.Red,
                textAlign = TextAlign.Center,
            )
        },
        modifier = Modifier.background(color = LightOrange),
        navigationIcon = {
            // Back button
            IconButton(
                onClick = {
                    // Handle back button click using onBackClick callback
                    onBackClick()
                },
                modifier = Modifier.padding(4.dp),
                content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        },
        actions = {

            // Cart button
            IconButton(
                onClick = {
                    // Handle cart button click using onCartClick callback
                    onCartClick()
                },
                modifier = Modifier.padding(4.dp),
                content = {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            )

            // Display the number of items in the cart
            Text(text = cartItemsCount.toString(), color = colorResource(id = R.color.black))


            // Menu button
            IconButton(
                onClick = {
                    // Handle menu button click using onMenuClick callback
                    onMenuClick()
                },
                modifier = Modifier.padding(4.dp),
                content = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            )


        }
    )
}

// HomeScreen.kt
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    // Observe the menu items
    val menuItemsEntity = homeViewModel.allMenuItems.collectAsState().value


    Scaffold(
        topBar = {
            // Observe the cart state
            val cartItems = homeViewModel.cartItems.collectAsState().value
            // Display the cart items or handle them as needed
            // For example, you can show the number of items in the cart
            MyAppTopAppBar(
                onBackClick = {},
                onMenuClick = {},
                onCartClick = {
                              TasteOfBharatRouter.navigateTo(Screen.CartScreen)
                },
                cartItemsCount = cartItems.size
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(menuItemsEntity) { menuItem ->
                MenuItemCard(menuItem = menuItem)
            }
        }
    }
}



@Composable
fun MenuItemCard(menuItem: MenuItemEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding()

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            Text(text = menuItem.name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = menuItem.desc, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Price: $${menuItem.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

