package com.example.mckmembersapp.screens.login
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.mckmembersapp.components.Toast
import com.example.mckmembersapp.R
import com.example.mckmembersapp.components.NormalButton
import com.example.mckmembersapp.data.Resource
import com.example.mckmembersapp.screens.login.state.LoginUiEvent
import com.example.mckmembersapp.ui.theme.AppTheme

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val loginState by remember {
        viewModel.loginState
    }

    val authState by viewModel.loginRequestResult.collectAsState()
    LaunchedEffect(authState) {
        if (authState is Resource.Success && authState.data?.profile != null) {
            viewModel.saveProfile(authState.data!!.profile)
            navController.navigate("home_screen")
            viewModel.resetStates()
            Log.d("loginDataResponse", "ProfileData: ${authState.data}")
        }
    }
    when (authState) {
        is Resource.Idle -> {
        }
        is Resource.Loading -> {
            LinearProgressIndicator()
        }
        is Resource.Success -> {
            if (authState.data != null) {
                Toast(message = authState.data?.message.toString())
            }
        }
        is Resource.Error -> {
            Toast(message = authState.data.toString())
            Log.d("loginDataResponse", "errorMessage: ${authState.message.toString()}")
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backgroundColor)
    ) {
        // Background image
        Surface(modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
        ) {
            Column(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .imePadding()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                        Column(
                            modifier = Modifier
                                .padding(horizontal = AppTheme.dimens.paddingLarge)
                                .padding(bottom = AppTheme.dimens.paddingExtraLarge)
                        ) {

                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(128.dp)
                                    .padding(top = AppTheme.dimens.paddingSmall),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(data = R.drawable.ic_launcher_round)
                                    .crossfade(enable = true)
                                    .scale(Scale.FILL)
                                    .build(),
                                contentDescription = stringResource(id = R.string.login_heading_text)
                            )
                            LoginInputs(
                                loginState = loginState,
                                onEmailChange = { inputString ->
                                    viewModel.onUiEvent(
                                        loginUiEvent = LoginUiEvent.EmailChanged(
                                            inputString
                                        )
                                    )
                                },
                                onPasswordChange = { inputString ->
                                    viewModel.onUiEvent(
                                        loginUiEvent = LoginUiEvent.PasswordChanged(
                                            inputString
                                        )
                                    )
                                },
                            )
                            NormalButton(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.login_button_text),
                                onClick = {
                                    viewModel.onUiEvent(loginUiEvent = LoginUiEvent.Submit)
                                    if (loginState.isLoginSuccessful) {
                                        viewModel.performLogin(viewModel.loginState.value.username.trim(),
                                            viewModel.loginState.value.password.trim())

                                    }
                                }
                            )

                        }


                }

            }
        }
    }
}
//@Composable
//@Preview
//fun LoginScreenPreview() {
//    val navController = rememberNavController()
//    val viewModel = LoginViewModel() // Provide a dummy instance of LoginViewModel here
//    LoginScreen(navController = navController, viewModel = viewModel)
//}
fun Context.showToast(message: String) {
    android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
}


