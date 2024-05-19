package com.deletech.mckmembersapp.models.auth

data class ProfileData(
    val error: Boolean,
    val message: String,
    val profile: Profile,
    val status: Int,
    val token: String
)