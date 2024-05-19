package com.deletech.mckmembersapp.storage
import androidx.room.Database
import androidx.room.RoomDatabase
import com.deletech.mckmembersapp.daos.ProfileDao
import com.deletech.mckmembersapp.models.auth.Profile
import javax.inject.Singleton

@Singleton
@Database(entities = [Profile::class],version = 1,exportSchema = false)
 abstract class MCKDatabase :RoomDatabase() {
    abstract fun profileDao(): ProfileDao
 }
