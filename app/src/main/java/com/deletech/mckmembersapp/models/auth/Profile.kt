package com.deletech.mckmembersapp.models.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    val church_id: Int,
    val church_name: String,
    val circuit_name: String,
    val date_created: String,
    val first_name: String,
    val gender: String,
    val id_number: String,
    @PrimaryKey(autoGenerate = false)
    val member_id: Int,
    val mobile_number: String,
    val registration_id: Int,
    val second_name: String,
    val status: Int,
    val surname: String,
    val synod_name: String,
    val token: String
)