package com.example.tasteofbharat_feelthespice.screens


import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasteofbharat_feelthespice.data.Category
import com.example.tasteofbharat_feelthespice.data.HomeViewModel
import com.example.tasteofbharat_feelthespice.data.MenuItem
import com.example.tasteofbharat_feelthespice.ui.theme.LightOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopAppBar() {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    // Taskbar with back and menu options
    TopAppBar(
        title = {
            Text(
                text = "Taste of Bharat",
                style =  TextStyle(
                    fontSize = 37.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    )
                , color = Color.Red,
                textAlign = TextAlign.Center,

            )
        },
        modifier = Modifier.background(color = LightOrange),
        navigationIcon = {
            // Back button
            IconButton(
                onClick = {
                    // Handle back button click using onBackPressedDispatcher
                    onBackPressedDispatcher?.onBackPressed()
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

            IconButton(
                onClick = { /* Handle menu click */ },
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

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val selectedCategory = homeViewModel.selectedCategory.collectAsState().value

    MyAppTopAppBar()

    if (selectedCategory != null) {
        // If a category is selected, show the MenuScreen
        MenuScreen(
            category = selectedCategory,
            menuItems = homeViewModel.menuItems.collectAsState().value,
            onBack = { homeViewModel.clearSelectedCategory() }
        )
    } else {
        // If no category is selected, show the list of categories
        val categories = homeViewModel.categories

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(45.dp)
        ) {
            items(categories) { category ->
                CategoryItem(category = category) { homeViewModel.selectCategory(category) }
            }
        }
    }
    BackHandler(enabled = homeViewModel.selectedCategory.value != null) {
        homeViewModel.clearSelectedCategory()
    }
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
fun CategoryItem(category: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp)
            .clickable { onClick() }

    ) {
        Text(
            text = category.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}


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

@Preview
@Composable
fun PreviewOfHomeScreen() {
    HomeScreen( homeViewModel = HomeViewModel())
}