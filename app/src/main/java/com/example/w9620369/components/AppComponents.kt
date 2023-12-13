package com.example.w9620369.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasteofbharat_feelthespice.R
import com.example.w9620369.ui.theme.LightOrange
import com.example.w9620369.ui.theme.Primary
import com.example.w9620369.ui.theme.Red
import com.example.w9620369.ui.theme.TextColor
import com.example.w9620369.ui.theme.componentShapes


@Composable
fun GeneralTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,

        )
        , color = TextColor,
        textAlign = TextAlign.Center
    )

}

@Composable
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,

            )
        , color = TextColor,
        textAlign = TextAlign.Center
    )

}
@Composable
fun AppNameTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,

            )
        , color = Red,
        textAlign = TextAlign.Center
    )

}
@Composable
fun AppTagTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,

            )
        , color = Red,
        textAlign = TextAlign.Right
    )

}

@ExperimentalMaterial3Api
@Composable
fun TextField(labelValue: String,
              painterResource: Painter,
              onTextSelected: (String) -> Unit,
              errorStatus: Boolean = false){
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = {Text(text = labelValue)},

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray,
            focusedLabelColor = Color.DarkGray,
            cursorColor = Primary,
        ),

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource,contentDescription =" ")
        },
        isError = !errorStatus

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String,
                      painterResource: Painter,onTextSelected: (String) -> Unit,
                      errorStatus: Boolean = false){
    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),
        label = {Text(text = labelValue)},

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray,
            focusedLabelColor = Color.DarkGray,
            cursorColor = Primary,
        ),

        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },

        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource,contentDescription =" ")
        },
        trailingIcon = {
            val iconImage = if(passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }
            var description = if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }else{
                stringResource(id = R.string.show_password)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value}) {
                Icon(imageVector = iconImage, contentDescription = description )
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false){
    Button(
        onClick = {  onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Red, Red)),
                    shape = RoundedCornerShape(50.dp),

                    ),
            contentAlignment = Alignment.Center

        ){Text(text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
            )

        }
    }

}

@Composable
fun Button2Component(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false){
    Button(
        onClick = {  onButtonClicked.invoke() },
        modifier = Modifier
            .width(20.dp)
            .heightIn(20.dp),

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled)






        {Text(text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        }
    }


@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically)
    {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Red,
            thickness = 1.dp
        )
        Text(modifier = Modifier.padding(8.dp),text = stringResource(id = R.string.or), fontSize = 18.sp, color = TextColor)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Red,
            thickness = 1.dp)
    }
}

@Composable
fun ClickableLoginTextComponent(tryingtoLogin: Boolean=true, onTextSelected: (String) -> Unit) {

    val initialText = if(tryingtoLogin){"Already have an account? "} else{
        "Don't have an account yet?  "
    }

    val loginText = if(tryingtoLogin){"Login"} else{"Register"}

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Red )) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
        ClickableText(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
            style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center),
            text = annotatedString, onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also {span ->
                Log.d("ClickableLoginTextComponent", "{${span.item}}")
                if(span.item==loginText){
                    onTextSelected(span.item)

                }
            }
        }
        )
}

@Composable
fun UnderlinedClickableTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,

            )
        , color = TextColor,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )

}


@Composable
fun smallButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = true){
    Button(
        onClick = {  onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(LightOrange, LightOrange)),
                    shape = RoundedCornerShape(50.dp),

                    ),
            contentAlignment = Alignment.Center

        ){Text(text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit,
    onCartClick: () -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    // Top app bar with back button
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
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
        title = {
            Text(
                text = title,
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
            // Ensure that this block is inside a @Composable function
            // This is a typical structure of a @Composable function
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


                IconButton(
                    onClick = {

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
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {

                }

        }
    )


}





