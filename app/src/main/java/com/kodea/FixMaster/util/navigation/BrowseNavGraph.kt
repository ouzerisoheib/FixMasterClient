package com.kodea.FixMaster.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kodea.FixMaster.presentation.CategoryList.CategoryListScreen
import com.kodea.FixMaster.presentation.booking.BookingScreen
import com.kodea.FixMaster.presentation.home.HomeScreen
import com.kodea.FixMaster.presentation.notification.NotificationScreen
import com.kodea.FixMaster.presentation.proProfile.ProProfileScreen
import com.kodea.FixMaster.presentation.search.SearchScreen
import com.kodea.FixMaster.presentation.settings.SettingsScreen

@Composable
fun BrowseNavGraph (navController : NavHostController){
    NavHost(navController = navController, route = Route.BrowseNavigation.route , startDestination = Route.HomeScreen.route){
        composable(route = Route.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(route = Route.BookingScreen.route){
            BookingScreen(navController)
        }
        composable(route = Route.SettingsScreen.route){
            SettingsScreen(navController)
        }
        composable(route = Route.SearchScreen.route){
            SearchScreen(navController)
        }
        composable(route = Route.NotificationScreen.route){
            NotificationScreen(navController = navController)
        }
        composable(route = Route.CategoryListScreen.route){
            CategoryListScreen(navController = navController)
        }
        composable(route = Route.ProProfileScreen.route){
            ProProfileScreen(navController = navController)
        }
    }
}