package com.kodea.FixMaster.presentation.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun NotificationScreen(navController : NavHostController){
    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
        Text(text = "Notification history" , fontSize = 24.sp)
    }
}