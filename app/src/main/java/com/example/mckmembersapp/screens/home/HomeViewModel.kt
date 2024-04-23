package com.example.mckmembersapp.screens.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mckmembersapp.data.Resource
import com.example.mckmembersapp.models.auth.Profile
import com.example.mckmembersapp.models.memberreport.MemberReportData
import com.example.mckmembersapp.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MemberRepository) : ViewModel(){
    private val _isLoading = MutableStateFlow(true)
    private val _reportDataRequestResult = MutableStateFlow<Resource<MemberReportData>>(Resource.Idle())
    val memberReportRequestResult: StateFlow<Resource<MemberReportData>> = _reportDataRequestResult
    fun getMemberReportData(token:String,memberId: String, churchId: String) {
        viewModelScope.launch {
            _reportDataRequestResult.value = Resource.Loading()
            try {
                val reportResponse = repository.memberReport(token,memberId, churchId)
                if (reportResponse is Resource.Success) {
                    _isLoading.value = false
                    _reportDataRequestResult.value = Resource.Success(reportResponse.data!!)
                } else {
                    _reportDataRequestResult.value = Resource.Error(reportResponse.message)
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _reportDataRequestResult.value = Resource.Error("Report failed: ${e.message}")
            }
        }
    }
    private val _profileResult = MutableStateFlow<Resource<Profile>>(Resource.Idle())
    val profileRequestResult: StateFlow<Resource<Profile>> = _profileResult
    fun getProfile() {
        viewModelScope.launch {
            _profileResult.value =Resource.Loading()
            try {
                val profileLiveData = repository.getProfile()
                profileLiveData.observeForever { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _profileResult.value =
                                Resource.Loading()
                        }
                        is Resource.Success -> {
                            _profileResult.value = resource
                            _isLoading.value = false
                        }
                        is Resource.Error -> {
                            _profileResult.value = resource
                            _isLoading.value = false
                        }
                        else -> {}
                    }
                }
            } catch (e: Exception) {
                // Handle any exceptions
                _profileResult.value = Resource.Error("Error: ${e.message}")
            }
        }
    }
}