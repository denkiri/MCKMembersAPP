package com.deletech.mckmembersapp.di
import android.content.Context
import androidx.room.Room
import com.deletech.mckmembersapp.endpoint.Endpoints
import com.deletech.mckmembersapp.storage.MCKDatabase
import com.deletech.mckmembersapp.utils.Constants
import com.deletech.mckmembersapp.utils.Constants.LOGIN_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAuthApi(): Endpoints {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoints::class.java)
    }
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MCKDatabase {
        return Room.databaseBuilder(
            context,
            MCKDatabase::class.java,
            LOGIN_DATABASE
        ).build()
    }
}