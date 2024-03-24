package com.example.mckmembersapp.screens.splashscreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mckmembersapp.R
@Composable
fun SplashScreen(navController: NavController) {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(
                    Color.Black
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center


        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.ic_launcher_round),
                contentDescription = "app Logo"
            )

        }
    } else {
        Box(
            modifier = Modifier
                .background(
                  Color.White

                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center


        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.ic_launcher_round),
                contentDescription = "app Logo"
            )

        }
    }


}
@Composable
@Preview
fun SplashScreenView(){
    SplashScreen(navController = rememberNavController())
}