package com.deletech.mckmembersapp.screens.login.state
import com.deletech.mckmembersapp.components.ErrorState
/**
 * Login State holding ui input values
 */
data class LoginState(
    val username: String = "",
    val password: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
    val isLoginSuccessful: Boolean = false
)


data class LoginErrorState(
    val usernameErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState()
)

