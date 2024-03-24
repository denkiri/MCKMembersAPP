package com.example.mckmembersapp.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mckmembersapp.screens.login.LoginViewModel
import com.example.mckmembersapp.screens.login.LoginScreen
import com.example.mckmembersapp.screens.splashscreen.SplashScreen
import kotlinx.coroutines.delay

@ExperimentalComposeUiApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "splash" ){

        composable("splash") {
            SplashScreen(navController = navController)
            LaunchedEffect(key1 = true) {
                delay(3000) // Delay for 3 seconds (adjust as needed)
                navController.navigate("login")
            }

        }
        composable("login") {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController = navController,viewModel=loginViewModel)
        }
    }

}