package com.example.w9620369.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w9620369.components.ButtonComponent
import com.example.w9620369.navigation.Screen
import com.example.w9620369.navigation.TasteOfBharatRouter
import com.example.w9620369.ui.theme.LightOrange


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PaymentScreen() {
    // Provide a ViewModel or any other data source to observe and store the selected option
    var selectedOption by remember { mutableStateOf<Option?>(null) }

    Scaffold(
        topBar = {PaymentTopAppBar(
            onBackClick = {TasteOfBharatRouter.navigateTo(Screen.CartScreen)},
            onMenuClick = {},
            isLoggedIn = true)

        }
    ) {
        // Create a radio group for the options
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RadioOption(
                option = Option.COLLECT_AT_RESTAURANT,
                isSelected = selectedOption == Option.COLLECT_AT_RESTAURANT,
                onSelected = { selectedOption = it },
                displayText = "Pay at Counter - Collect at Restaurant"
            )

            Spacer(modifier = Modifier.height(16.dp))

            RadioOption(
                option = Option.CASH_ON_DELIVERY,
                isSelected = selectedOption == Option.CASH_ON_DELIVERY,
                onSelected = { selectedOption = it },
                displayText = "Cash on Delivery - Delivery"
            )

            // Display the selected option
            Spacer(modifier = Modifier.height(16.dp))
            selectedOption?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text("You have chosen to${selectedOption?.selectedText.orEmpty()}", fontSize = 24.sp, textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.height(50.dp))
            ButtonComponent("Confirm Order",
                onButtonClicked = {
                        TasteOfBharatRouter.navigateTo(Screen.OrderConfirmedScreen)
                    },isEnabled = selectedOption != null)
        }




    }

}


// Enum representing the available options
enum class Option( val selectedText: String) {
    COLLECT_AT_RESTAURANT(" pay at counter and collect your food. See you soon!"),
    CASH_ON_DELIVERY( " have your food at your doorstep and pay the delivery partner")
}

// Composable for a single radio button option
@Composable
fun RadioOption(
    option: Option,
    isSelected: Boolean,
    onSelected: (Option) -> Unit,
    displayText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = { onSelected(option) }
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelected(option) },
            modifier = Modifier
                .padding(4.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                text = displayText,
                fontSize = 20.sp
            )


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTopAppBar(onBackClick: () -> Unit, onMenuClick: () -> Unit, isLoggedIn: Boolean) {

    // Top app bar with back button
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClick(
                    )
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
        title = {
            Text(
                text = "Payment Options",
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
        actions = {


        }



    )
}

