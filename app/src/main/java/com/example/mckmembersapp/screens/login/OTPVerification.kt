package com.example.mckmembersapp.screens.login

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mckmembersapp.R
import com.example.mckmembersapp.components.NormalButton
import com.example.mckmembersapp.ui.theme.md_theme_light_primary
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

@Composable
fun OTPVerification(navController: NavController){
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backgroundColor)
    ) {
        // Background image
        Surface(modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
        ) {
            FirebaseUI(LocalContext.current,navController)
        }
    }
}
@Composable
fun FirebaseUI(context: Context,navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val phoneNumber = remember {
        mutableStateOf("")
    }

    val otp = remember {
        mutableStateOf("")
    }

    val verificationID = remember {
        mutableStateOf("")
    }

    val message = remember {
        mutableStateOf("")
    }
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var callbacks: OnVerificationStateChangedCallbacks
    Column(
        // adding modifier for our column
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = backgroundColor),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = phoneNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { phoneNumber.value = it },
            placeholder = { Text(text = "Enter your phone number") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = textColor, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(10.dp))
        NormalButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.generate_otp),
            onClick = {
                if (TextUtils.isEmpty(phoneNumber.value)) {
                    Toast.makeText(context, "Please enter phone number..", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val number ="+254${phoneNumber.value}"
                    sendVerificationCode(number, mAuth, context as Activity, callbacks)
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = otp.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { otp.value = it },
            placeholder = { Text(text = "Enter your otp") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = textColor, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(10.dp))
        NormalButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.verify_otp),
            onClick = {
                if (TextUtils.isEmpty(otp.value.toString())) {
                    Toast.makeText(context, "Please enter otp..", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        verificationID.value, otp.value
                    )
                    signInWithPhoneAuthCredential(
                        credential,
                        mAuth,
                        context as Activity,
                        context,
                        message
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = message.value,
            style = TextStyle(
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
    callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            message.value = "Verification successful"
            Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
            navController.navigate("change_password")
        }
        override fun onVerificationFailed(p0: FirebaseException) {
            message.value = "Fail to verify user : \n" + p0.message
            Toast.makeText(context, "Verification failed..", Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(verificationId: String, p1: ForceResendingToken) {
            super.onCodeSent(verificationId, p1)
            verificationID.value = verificationId
        }
    }
}





private fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    auth: FirebaseAuth,
    activity: Activity,
    context: Context,
    message: MutableState<String>
) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                message.value = "Verification successful"
                Toast.makeText(context, "Verification successful..", Toast.LENGTH_SHORT).show()
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(
                        context,
                        "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}
private fun sendVerificationCode(
    number: String,
    auth: FirebaseAuth,
    activity: Activity,
    callbacks: OnVerificationStateChangedCallbacks
) {
    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)
        .setCallbacks(callbacks)
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun OTPVerificationPrev(){
    OTPVerification(rememberNavController())
}