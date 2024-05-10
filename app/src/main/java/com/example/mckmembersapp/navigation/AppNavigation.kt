package com.example.mckmembersapp.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mckmembersapp.screens.home.CustomReportScreen
import com.example.mckmembersapp.screens.home.HomeScreen
import com.example.mckmembersapp.screens.home.HomeViewModel
import com.example.mckmembersapp.screens.home.Preview2
import com.example.mckmembersapp.screens.login.ChangePasswordScreen
import com.example.mckmembersapp.screens.login.LoginViewModel
import com.example.mckmembersapp.screens.login.LoginScreen
import com.example.mckmembersapp.screens.login.OTPVerification
import com.example.mckmembersapp.screens.login.ResetDefaultPassword
import com.example.mckmembersapp.screens.splashscreen.SplashScreen
import kotlinx.coroutines.delay

@ExperimentalComposeUiApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            SplashScreen(navController = navController, viewModel = loginViewModel)
        }
        composable("login") {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController = navController, viewModel = loginViewModel)
        }

        composable("home_screen") {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController = navController, viewModel = homeViewModel)
        }
        composable("custom_report_screen") {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            CustomReportScreen(navController = navController, viewModel = homeViewModel)
        }
        composable("change_password") {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            ChangePasswordScreen(navController = navController, viewModel = loginViewModel)
        }
        composable("reset_default_password") {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            ResetDefaultPassword(navController = navController, viewModel = loginViewModel)
        }
        composable("otp_verification") {
            OTPVerification(navController = navController)
        }
        composable(
            route = "preview/{receiptNumber}/{status}/{date}/{firstName}/{secondName}/{surname}/{amount}",
            arguments = listOf(
                navArgument("receiptNumber") { type = NavType.StringType },
                navArgument("status") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("firstName") { type = NavType.StringType },
                navArgument("secondName") { type = NavType.StringType },
                navArgument("surname") { type = NavType.StringType },
                navArgument("amount") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val receiptNumber = backStackEntry.arguments?.getString("receiptNumber")
            val status = backStackEntry.arguments?.getString("status")
            val date = backStackEntry.arguments?.getString("date")
            val firstName = backStackEntry.arguments?.getString("firstName")
            val secondName = backStackEntry.arguments?.getString("secondName")
            val surname = backStackEntry.arguments?.getString("surname")
            val amount = backStackEntry.arguments?.getFloat("amount")

            val homeViewModel = hiltViewModel<HomeViewModel>()
            Preview2(
                receiptNumber = receiptNumber,
                status = status,
                date = date,
                firstName = firstName,
                secondName = secondName,
                surname = surname,
                amount = amount?.toDouble()
            )
        }
    }
}
