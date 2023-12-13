package com.example.w9620369.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.tasteofbharat_feelthespice.R
import com.example.w9620369.home.HomeViewModel
import com.example.w9620369.menu.MenuItem
import com.example.w9620369.navigation.Screen
import com.example.w9620369.navigation.TasteOfBharatRouter
import com.example.w9620369.ui.theme.LightOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopAppBar(onBackClick: () -> Unit, onMenuClick: () -> Unit, onCartClick: () -> Unit) {

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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val menuItems by homeViewModel.allMenuItems.collectAsState()
    val cartItems = homeViewModel.cartItems.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightOrange)
    ) {
        MyAppTopAppBar(onBackClick = {}, onMenuClick = {}, onCartClick = {
            TasteOfBharatRouter.navigateTo(Screen.CartScreen)
        })
        HomeContent(menuItems, homeViewModel)
    }
}




@Composable
private fun HomeContent(menuItems: List<MenuItem>, homeViewModel: HomeViewModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = colorResource(R.color.light_orange),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.light_orange))
                .padding(28.dp)
        ) {
            if (menuItems.isEmpty()) {
                // Display a message or loading indicator when there are no menu items
                Text(text = "No menu items available", modifier = Modifier.padding(16.dp))
            } else {
                MenuItemsList(menuItems, homeViewModel,onQuantityChanged={ menuItem, quantity -> homeViewModel.addToCart(menuItem, quantity)
                })
            }
        }
    }
}

@Composable
private fun MenuItemsList(menuItems: List<MenuItem>, homeViewModel: HomeViewModel,
                          onQuantityChanged: (MenuItem, Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItemCard(
                menuItem = menuItem,
                onAddToCartClick = { item, quantity ->
                    homeViewModel.addToCart(item, quantity)
                },
                onQuantityChanged= onQuantityChanged
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MenuItemCard(
    menuItem: MenuItem,
    onAddToCartClick: (MenuItem, Int) -> Unit,
    onQuantityChanged: (MenuItem, Int) -> Unit
) {
    // Use remember to track the local quantity state
            var quantity by remember(menuItem) { mutableStateOf(0) }

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
                    // Image
                    Image(
                        painter = rememberImagePainter(menuItem.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), // Adjust the height as needed
                        contentScale = ContentScale.Crop
                    )

                    // Item details
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = menuItem.name, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = menuItem.desc, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Price: £${menuItem.price}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    // Quantity selector with +/- buttons
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (quantity > 0) {
                                        quantity--
                                        onQuantityChanged(menuItem, quantity)
                                    }
                                },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                                    .clip(MaterialTheme.shapes.medium)
                            ) {
                                Text(text = "-", color = Color.White)
                            }

                            OutlinedTextField(
                                value = quantity.toString(),
                                onValueChange = {
                                    // Handle the case where the user inputs a non-numeric value
                                    quantity = it.toIntOrNull() ?: 0
                                },
                                label = {
                                    Text(
                                        "Quantity:",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                                singleLine = true,
                                modifier = Modifier
                                    .width(100.dp)
                                    .padding(horizontal = 8.dp)
                            )

                            IconButton(
                                onClick = {
                                    quantity++

                                },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                                    .clip(MaterialTheme.shapes.medium)
                            ) {
                                Text(text = "+", color = Color.White)
                            }
                        }

                        // Add to Cart button
                        Button(
                            onClick = {
                                onAddToCartClick(menuItem, quantity)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text("Add to Cart")
                        }
                    }
                }
            }
        }

