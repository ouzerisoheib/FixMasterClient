package com.kodea.FixMaster.presentation.authentification.passwordReset

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun PasswordReset(){
    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
        Text(text = "Password Reset" , fontSize = 24.sp)
    }
}