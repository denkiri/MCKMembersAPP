package com.deletech.mckmembersapp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.deletech.mckmembersapp.screens.login.LoginInputs
import com.deletech.mckmembersapp.screens.login.state.LoginState
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testUsernameInput() {
        val testUsername = "0795445138"
        val loginState = mutableStateOf(LoginState(username = ""))
        composeTestRule.setContent {
            LoginInputs(
                loginState = loginState.value,
                onEmailChange = { newUsername -> loginState.value = loginState.value.copy(username = newUsername) },
                onPasswordChange = {}
            )
        }
        composeTestRule.onNodeWithTag("mobile")
            .performTextInput(testUsername)
//        // Verify the text input
//        composeTestRule.onNodeWithTag("mobile")
//            .assertTextEquals(testUsername)
    }
}

