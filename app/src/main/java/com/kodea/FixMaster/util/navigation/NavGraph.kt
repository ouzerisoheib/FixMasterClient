package com.kodea.FixMaster.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.kodea.FixMaster.presentation.authentification.passwordReset.PasswordReset
import com.kodea.FixMaster.presentation.authentification.signIn.SignInScreen
import com.kodea.FixMaster.presentation.authentification.signUp.SignUpScreen
import com.kodea.FixMaster.presentation.onBoarding.OnBoardingScreen

@Composable
fun NavGraph(startDestination: String) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                OnBoardingScreen(navController = navController)
            }
        }
        composable(route = Route.BrowseNavigation.route) {
            BrowseScreen()
        }


        navigation(
            route = Route.AuthNavigation.route,
            startDestination = AuthScreens.SignInScreen.route
        ) {
            composable(route = AuthScreens.SignInScreen.route) {
                SignInScreen(navController)
            }
            composable(route = AuthScreens.SignUpScreen.route + "/{lat}" + "/{lon}") {
                SignUpScreen(navController)
            }
            composable(route = AuthScreens.PasswordReset.route) {
                PasswordReset()
            }
        }


    }
}