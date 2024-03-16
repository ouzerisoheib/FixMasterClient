package com.kodea.FixMaster.util.navigation

sealed class Route(val route : String) {
    object OnBoardingScreen : Route("onBoardingScreen")
    object HomeScreen : Route("homeScreen")
    object AppStartNavigation : Route("appStartNavigation")
    object BrowseNavigation : Route("browseNavigation")
    object BrowseNavigatorScreen : Route("browseNavigatorScreen")
    object AuthNavigation : Route("authNavigation")
    object BookingScreen : Route("bookingScreen")
    object SettingsScreen : Route("settingsScreen")
    object SearchScreen : Route("searchScreen")
    object NotificationScreen : Route("notificationScreen")
    object CategoryListScreen : Route("categoryListScreen")
    object ProProfileScreen : Route("proProfileScreen")
}