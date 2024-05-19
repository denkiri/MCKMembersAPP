package com.deletech.mckmembersapp.components

import androidx.annotation.StringRes
import com.deletech.mckmembersapp.R


/**
 * Error state holding values for error ui
 */
data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)