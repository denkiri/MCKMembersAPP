package com.example.mckmembersapp.repository
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mckmembersapp.daos.ProfileDao
import com.example.mckmembersapp.data.Resource
import com.example.mckmembersapp.endpoint.Endpoints
import com.example.mckmembersapp.models.auth.Profile
import com.example.mckmembersapp.models.memberreport.MemberReportData
import com.example.mckmembersapp.storage.MCKDatabase
import retrofit2.await
import javax.inject.Inject
class MemberRepository @Inject constructor(private val api: Endpoints, db: MCKDatabase, application: Application){
    private val profileDao: ProfileDao
    private val context: Context
        init {
            profileDao = db.profileDao()
            context=application.applicationContext
        }
        suspend fun memberReport(authToken:String?,memberId: String?,churchId: String?): Resource<MemberReportData> {
            return try {
                Resource.Loading(data = true)
                val response = api.memberReport(authToken,memberId,churchId).await()
                Resource.Loading(data = false)

                run {
                    Resource.Success(data = response)
                }
            } catch (exception: Exception) {
                Resource.Error(message = exception.message.toString())
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
