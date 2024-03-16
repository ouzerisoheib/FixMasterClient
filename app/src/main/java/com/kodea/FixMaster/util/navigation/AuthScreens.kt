package com.kodea.FixMaster.util.navigation

sealed class AuthScreens(val route: String) {
    object SignInScreen : AuthScreens("signInScreen")
    object SignUpScreen : AuthScreens("signUpScreen")
    object PasswordReset : AuthScreens("passwordReset")
}