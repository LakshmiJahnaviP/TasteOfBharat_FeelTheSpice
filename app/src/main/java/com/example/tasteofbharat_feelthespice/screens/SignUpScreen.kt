package com.example.tasteofbharat_feelthespice.screens

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
import com.example.tasteofbharat_feelthespice.components.AppNameTextComponent
import com.example.tasteofbharat_feelthespice.components.ButtonComponent
import com.example.tasteofbharat_feelthespice.components.ClickableLoginTextComponent
import com.example.tasteofbharat_feelthespice.components.DividerTextComponent
import com.example.tasteofbharat_feelthespice.components.GeneralTextComponent
import com.example.tasteofbharat_feelthespice.components.HeadingTextComponent
import com.example.tasteofbharat_feelthespice.components.PasswordTextField
import com.example.tasteofbharat_feelthespice.components.TextField
import com.example.tasteofbharat_feelthespice.components.smallButtonComponent
import com.example.tasteofbharat_feelthespice.data.signUp.SignUpViewModel
import com.example.tasteofbharat_feelthespice.data.signUp.SignupUIEvent
import com.example.tasteofbharat_feelthespice.navigation.Screen
import com.example.tasteofbharat_feelthespice.navigation.TasteOfBharatRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(signupViewModel: SignUpViewModel = viewModel()) {
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
                GeneralTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                TextField(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                TextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.email),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )
                Spacer(modifier = Modifier.height(80.dp))


                ButtonComponent(value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingtoLogin = true, onTextSelected = {
                    TasteOfBharatRouter.navigateTo(Screen.LoginScreen)

                })

                smallButtonComponent(
                    value = stringResource(id = R.string.noLogin),
                    onButtonClicked = {
                        TasteOfBharatRouter.navigateTo(Screen.HomeScreen)
                    }
                )
            }
        }
        if(signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }
}
@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}
