package com.kodea.FixMaster.util.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import com.kodea.FixMaster.R

sealed class BottomScreenNavigation(val title : String , val selectedIcon : Int,val unselectedIcon : Int ,val route : String) {
    object Home : BottomScreenNavigation(title = "Home" , selectedIcon = R.drawable.filled_home, unselectedIcon = R.drawable.outlined_home , route = Route.HomeScreen.route)
    object Booking : BottomScreenNavigation(title = "Booking" , selectedIcon = R.drawable.filled_booking,unselectedIcon = R.drawable.outlined_booking , route = Route.BookingScreen.route)
    object Settings : BottomScreenNavigation(title = "Settings" , selectedIcon = R.drawable.filled_settings, unselectedIcon = R.drawable.outlined_settings , route = Route.SettingsScreen.route)
    object Search : BottomScreenNavigation(title = "Search" , selectedIcon = R.drawable.filled_search ,unselectedIcon = R.drawable.outlined_search , route = Route.SearchScreen.route)
}