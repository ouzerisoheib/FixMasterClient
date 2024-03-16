package com.kodea.FixMaster.util.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kodea.FixMaster.presentation.common.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BrowseScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(bottomBar = {
        BottomBar(navController)
    }) {
        Box(Modifier.fillMaxSize().padding(it)) {
            BrowseNavGraph(navController = navController)
        }
    }
}



