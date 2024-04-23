package com.example.mckmembersapp.screens.login
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mckmembersapp.components.ErrorState
import com.example.mckmembersapp.data.Resource
import com.example.mckmembersapp.models.auth.Profile
import com.example.mckmembersapp.models.auth.ProfileData
import com.example.mckmembersapp.repository.LoginRepository
import com.example.mckmembersapp.screens.login.state.LoginErrorState
import com.example.mckmembersapp.screens.login.state.LoginState
import com.example.mckmembersapp.screens.login.state.LoginUiEvent
import com.example.mckmembersapp.screens.login.state.emailEmptyErrorState
import com.example.mckmembersapp.screens.login.state.passwordEmptyErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel(){
    var loginState = mutableStateOf(LoginState())
    fun resetStates() {
        loginState.value = LoginState()
//        _loginRequestResult.value = Resource.Idle()
//        _profileResult.value=Resource.Idle()
    }

    private val _isLoading = MutableStateFlow(true) // Initialize as true to start loading
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onUiEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {

            // Email/Mobile changed
            is LoginUiEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    username = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        usernameErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            emailEmptyErrorState
                    )
                )
            }

            // Password changed
            is LoginUiEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    password = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorState = if (loginUiEvent.inputValue.trim().isNotEmpty())
                            ErrorState()
                        else
                            passwordEmptyErrorState
                    )
                )
            }

            // Submit Login
            is LoginUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    isLoading
                    // TODO Trigger login in authentication flow
                    loginState.value = loginState.value.copy(isLoginSuccessful = true)


                }
            }
        }
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (usecase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    private fun validateInputs(): Boolean {
        val emailOrMobileString = loginState.value.username.trim()
        val passwordString = loginState.value.password
        return when {

            // Email/Mobile empty
            emailOrMobileString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        usernameErrorState = emailEmptyErrorState
                    )
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorState = passwordEmptyErrorState
                    )
                )
                false
            }
            // No errors
            else -> {
                // Set default error state
                loginState.value = loginState.value.copy(username = emailOrMobileString)

                loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }
    private val _loginRequestResult = MutableStateFlow<Resource<ProfileData>>(Resource.Idle())
    val loginRequestResult: StateFlow<Resource<ProfileData>> = _loginRequestResult
    fun performLogin(mobileNumber: String, password: String) {
        viewModelScope.launch {
            _loginRequestResult.value = Resource.Loading()
            try {
                val loginResponse = repository.loginMember(mobileNumber, password)
                if (loginResponse is Resource.Success) {
                    _isLoading.value = false
                    _loginRequestResult.value = Resource.Success(loginResponse.data!!)
                } else {
                    _loginRequestResult.value = Resource.Error(loginResponse.message)
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _loginRequestResult.value = Resource.Error("Login failed: ${e.message}")
            }
        }
    }
    private val _profileResult = MutableStateFlow<Resource<Profile>>(Resource.Idle())
    val profileResult: StateFlow<Resource<Profile>> = _profileResult
    fun getProfile() {

        viewModelScope.launch {
            try {
                // Call the repository function to get LiveData<Resource<Profile>>
                val profileLiveData = repository.getProfile()

                // Observe the LiveData from the repository to handle Resource states
                profileLiveData.observeForever { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _profileResult.value =
                                Resource.Loading() // Notify UI that profile data is loading
                        }

                        is Resource.Success -> {
                            // Update _profileResult with the success data
                            _profileResult.value = resource
                            _isLoading.value = false
                        }

                        is Resource.Error -> {
                            // Update _profileResult with the error message
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
    fun saveProfile(data: Profile){
        repository.saveProfile(data)
    }


}