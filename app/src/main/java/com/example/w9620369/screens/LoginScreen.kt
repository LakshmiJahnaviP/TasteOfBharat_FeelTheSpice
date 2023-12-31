package com.example.w9620369.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasteofbharat_feelthespice.R
import com.example.w9620369.components.AppNameTextComponent
import com.example.w9620369.components.AppTagTextComponent
import com.example.w9620369.components.ButtonComponent
import com.example.w9620369.components.ClickableLoginTextComponent
import com.example.w9620369.components.DividerTextComponent
import com.example.w9620369.components.GeneralTextComponent
import com.example.w9620369.components.HeadingTextComponent
import com.example.w9620369.components.PasswordTextField
import com.example.w9620369.components.TextField
import com.example.w9620369.components.UnderlinedClickableTextComponent
import com.example.w9620369.components.smallButtonComponent
import com.example.w9620369.data.login.LoginUIEvent
import com.example.w9620369.data.login.LoginViewModel
import com.example.w9620369.navigation.Screen
import com.example.w9620369.navigation.SystemBackButtonHandler
import com.example.w9620369.navigation.TasteOfBharatRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()){
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
        )
        {
            Column(modifier = Modifier.fillMaxSize()) {
                AppNameTextComponent(value = stringResource(id = R.string.appName))
                AppTagTextComponent(value = stringResource(id = R.string.appTag))
                HeadingTextComponent(value = stringResource(id = R.string.welcome_back))
                GeneralTextComponent(value = stringResource(id = R.string.login))
                TextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    }
                )
                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                UnderlinedClickableTextComponent(value = stringResource(id = R.string.forgot_password))
                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(40.dp))
                DividerTextComponent()
                ClickableLoginTextComponent(tryingtoLogin = false, onTextSelected = {
                    TasteOfBharatRouter.navigateTo(Screen.SignUpScreen)
                })

                smallButtonComponent(
                    value = stringResource(id = R.string.noLogin),
                    onButtonClicked = {
                        TasteOfBharatRouter.navigateTo(Screen.HomeScreen)
                    }
                )


            }
        }
        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }

    }
    SystemBackButtonHandler{
        TasteOfBharatRouter.navigateTo(Screen.SignUpScreen)
    }

}

@Preview
@Composable
fun PreviewOfLoginScreen(){
    LoginScreen()
}