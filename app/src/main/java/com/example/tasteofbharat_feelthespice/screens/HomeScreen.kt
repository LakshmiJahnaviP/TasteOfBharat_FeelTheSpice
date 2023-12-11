package com.example.tasteofbharat_feelthespice.screens


import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.tasteofbharat_feelthespice.data.Category
import com.example.tasteofbharat_feelthespice.data.MenuItem
import com.example.tasteofbharat_feelthespice.data.entity.MenuItemEntity
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
    val menuItems = menuItemsEntity.map { it.toMenuItem() }

    Scaffold(
        topBar = {
            // Observe the cart state
            val cartItems = homeViewModel.cartItems.collectAsState().value
            // Display the cart items or handle them as needed
            // For example, you can show the number of items in the cart
            MyAppTopAppBar(
                onBackClick = {},
                onMenuClick = {},
                onCartClick = {},
                cartItemsCount = cartItems.size
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(menuItems) { menuItem ->
                MenuItemCard(menuItem = menuItem)
            }
        }
    }
}

fun MenuItemEntity.toMenuItem(): MenuItem {
    return MenuItem(
        name = this.name,
        description = this.desc,
        price = this.price

    )
}

@Composable
fun MenuScreen(category: Category, menuItems: List<MenuItem>, onBack: () -> Unit) {
    // Content of the screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = AppBarHeight) // Add padding to create space for the app bar
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Row with title and back button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = category.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            items(menuItems) { menuItem ->
                MenuItemCard(menuItem = menuItem)
            }
        }
    }
}

// Add this constant to define the height of the app bar
val AppBarHeight = 56.dp




@Composable
fun MenuItemCard(menuItem: MenuItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            Text(text = menuItem.name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = menuItem.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Price: $${menuItem.price}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

