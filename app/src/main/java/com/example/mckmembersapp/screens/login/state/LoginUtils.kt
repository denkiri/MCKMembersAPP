package com.example.mckmembersapp.screens.login.state
import com.example.mckmembersapp.components.ErrorState
import com.example.mckmembersapp.R

val emailEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_username
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)
val loginErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_user_not_found
)