package com.example.mckmembersapp.repository
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mckmembersapp.daos.ProfileDao
import com.example.mckmembersapp.data.Resource
import com.example.mckmembersapp.endpoint.Endpoints
import com.example.mckmembersapp.models.auth.Profile
import com.example.mckmembersapp.models.auth.ProfileData
import com.example.mckmembersapp.storage.MCKDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await
import javax.inject.Inject
class LoginRepository @Inject constructor(private val api: Endpoints, db: MCKDatabase, application: Application){
    private val profileDao: ProfileDao
    private val context: Context

    init {
        context=application.applicationContext
        profileDao=db.profileDao()
    }
    suspend fun loginMember(mobileNumber: String, password: String): Resource<ProfileData> {
        return try {
            Resource.Loading(data = true)
            val response = api.signIn(mobileNumber, password).await()
            Resource.Loading(data = false)

            run {
                Log.d("Response", "ProfileData: $response")
                Resource.Success(data = response)
            }
        } catch (exception: Exception) {
            Log.d("ErrorResponse", "Error: ${exception.message.toString()}")
            Resource.Error(message = exception.message.toString())
        }
    }
    suspend fun changeMemberPassword(mobileNumber: String, password: String): Resource<ProfileData> {
        return try {
            Resource.Loading(data = true)
            val response = api.changePassword(mobileNumber, password).await()
            Resource.Loading(data = false)

            run {
                Log.d("Response", "ProfileData: $response")
                Resource.Success(data = response)
            }
        } catch (exception: Exception) {
            Log.d("ErrorResponse", "Error: ${exception.message.toString()}")
            Resource.Error(message = exception.message.toString())
        }
    }
    suspend fun resetDefaultPassword(mobileNumber: String, password: String,defaultPassword: String): Resource<ProfileData> {
        return try {
            Resource.Loading(data = true)
            val response = api.resetDefaultPassword(mobileNumber, password,defaultPassword).await()
            Resource.Loading(data = false)

            run {
                Log.d("Response", "ProfileData: $response")
                Resource.Success(data = response)
            }
        } catch (exception: Exception) {
            Log.d("ErrorResponse", "Error: ${exception.message.toString()}")
            Resource.Error(message = exception.message.toString())
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun saveProfile(data: Profile) {
        GlobalScope.launch(context = Dispatchers.IO) {
            profileDao.delete()
            data.let { profileDao.insertProfile(it) }
        }
    }

    fun getProfile(): LiveData<Resource<Profile>> {
        val resultLiveData = MutableLiveData<Resource<Profile>>()
        resultLiveData.value = Resource.Loading()

        try {
            val profileLiveData = profileDao.getProfile()
            profileLiveData.observeForever { profile ->
                profile?.let {
                    resultLiveData.value = Resource.Success(profile)
                } ?: run {
                    resultLiveData.value = Resource.Error("Profile not found")
                }
            }
        } catch (exception: Exception) {
            resultLiveData.value = Resource.Error("Error: ${exception.message}")
        }

        return resultLiveData
    }






}