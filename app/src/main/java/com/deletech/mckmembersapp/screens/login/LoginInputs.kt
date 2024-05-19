package com.deletech.mckmembersapp.screens.login
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deletech.mckmembersapp.R
import com.deletech.mckmembersapp.components.PasswordTextField
import com.deletech.mckmembersapp.components.UsernameTextField
import com.deletech.mckmembersapp.screens.login.state.LoginState
import com.deletech.mckmembersapp.ui.theme.AppTheme

@Composable
fun LoginInputs(
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    val isDarkTheme = isSystemInDarkTheme()
    val textColor = if (isDarkTheme) Color.White else Color.Black

    // Login Inputs Section
    Column(modifier = Modifier.fillMaxSize() .padding(top = 128.dp),verticalArrangement = Arrangement.Center,) {
        Text(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            text = stringResource(id = R.string.login_heading_text),
            color= textColor
        )
        UsernameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),

            value = loginState.username,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.mobile),
            isError = loginState.errorState.usernameErrorState.hasError,
            errorText = stringResource(id = loginState.errorState.usernameErrorState.errorMessageStringResource)
        )


        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = loginState.password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.login_password_label),
            isError = loginState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = loginState.errorState.passwordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Done
        )

    }
}
@Composable
fun ChangePasswordInputs(
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    val isDarkTheme = isSystemInDarkTheme()
    val textColor = if (isDarkTheme) Color.White else Color.Black

    // Login Inputs Section
    Column(modifier = Modifier.fillMaxSize() .padding(top = 128.dp),verticalArrangement = Arrangement.Center,) {
        Text(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            text = stringResource(id = R.string.login_heading_text),
            color= textColor
        )
        UsernameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),

            value = loginState.username,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.mobile),
            isError = loginState.errorState.usernameErrorState.hasError,
            errorText = stringResource(id = loginState.errorState.usernameErrorState.errorMessageStringResource)
        )


        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = loginState.password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.change_password_label),
            isError = loginState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = loginState.errorState.passwordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Done
        )

    }
}
@Composable
fun RegisterInputs(
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,

    ) {

    // Login Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {

        // Email or Mobile Number
        UsernameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = loginState.username,
            onValueChange = onEmailChange,
            label = stringResource(id = R.string.mobile),
            isError = loginState.errorState.usernameErrorState.hasError,
            errorText = stringResource(id = loginState.errorState.usernameErrorState.errorMessageStringResource)
        )


        // Password
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = loginState.password,
            onValueChange = onPasswordChange,
            label = stringResource(id = R.string.login_password_label),
            isError = loginState.errorState.passwordErrorState.hasError,
            errorText = stringResource(id = loginState.errorState.passwordErrorState.errorMessageStringResource),
            imeAction = ImeAction.Done
        )





    }
}