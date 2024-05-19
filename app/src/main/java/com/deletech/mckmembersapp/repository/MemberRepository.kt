package com.deletech.mckmembersapp.repository
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deletech.mckmembersapp.daos.ProfileDao
import com.deletech.mckmembersapp.data.Resource
import com.deletech.mckmembersapp.endpoint.Endpoints
import com.deletech.mckmembersapp.models.auth.Profile
import com.deletech.mckmembersapp.models.memberreport.MemberReportData
import com.deletech.mckmembersapp.storage.MCKDatabase
import com.deletech.mckmembersapp.storage.setLoginStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    suspend fun customMemberReport(authToken:String?,memberId: String?,day:String?,month:String?,year:String?,churchId: String?): Resource<MemberReportData> {
        return try {
            Resource.Loading(data = true)
            val response = api.customMemberReport(authToken,memberId,day,month,year,churchId).await()
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
    suspend fun setLoginStatus(isLoggedIn: Boolean): Flow<Resource<Unit>> = flow {
        try {
            context.setLoginStatus(isLoggedIn)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message, data = null))
        }
    }

    }
