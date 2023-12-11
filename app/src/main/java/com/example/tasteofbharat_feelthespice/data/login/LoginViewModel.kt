package com.example.tasteofbharat_feelthespice.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tasteofbharat_feelthespice.data.signUp.Validator
import com.example.tasteofbharat_feelthespice.navigation.Screen
import com.example.tasteofbharat_feelthespice.navigation.TasteOfBharatRouter
import com.google.firebase.auth.FirebaseAuth



class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(  LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)


    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
            is LoginUIEvent.LogoutButtonClicked -> {
                logout()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    private fun login() {

        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG,"Inside_login_success")
                Log.d(TAG,"${it.isSuccessful}")
                loginInProgress.value = false
                if(it.isSuccessful){
                    TasteOfBharatRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG,"Inside_login_failure")
                Log.d(TAG,"${it.localizedMessage}")

                loginInProgress.value = false

            }

    }
    private fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Logged out successfully")
                TasteOfBharatRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Log out unsuccessful")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }

}