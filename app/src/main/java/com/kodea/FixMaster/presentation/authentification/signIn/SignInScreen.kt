package com.kodea.FixMaster.presentation.authentification.signIn

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.kodea.FixMaster.R
import com.kodea.FixMaster.presentation.authentification.common.OTPTextFields
import com.kodea.FixMaster.util.navigation.AuthScreens
import com.kodea.FixMaster.util.navigation.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SignInScreen(navController: NavHostController, context: Context = LocalContext.current) {
    var locationInfo: Location? = null
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val scope = rememberCoroutineScope()


    var locationRequired: Boolean = false
    val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val launcherMultiplePermissions =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissionMaps ->
            val areGaranted = permissionMaps.values.reduce { acc, b -> acc && b }
            if (areGaranted) {
                locationRequired = true
                Toast.makeText(context, "permission garanted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "permission deneid", Toast.LENGTH_LONG).show()
            }
        }


    /*scope.launch {
        if (permissions.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            //get Location
            locationInfo = locationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).await()

            *//*Toast.makeText(
                context,
                "${locationInfo?.latitude} and ${locationInfo?.longitude}",
                Toast.LENGTH_LONG
            ).show()*//*

        } else launcherMultiplePermissions.launch(permissions)
    }*/



    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .fillMaxHeight()
                .padding(top = 96.dp)
        ) {
            Text(
                text = stringResource(id = R.string.SignIn),
                fontSize = 40.sp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
            Text(
                text = stringResource(id = R.string.WelcomeBack),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(top = 36.dp)
        ) {

            var email = rememberSaveable { mutableStateOf("") }
            com.kodea.FixMaster.presentation.authentification.common.OutlinedTextField(
                value = email,
                placeHolder = "Email",
                label = "Email",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = null
                    )
                },
                trailingIcon = { /*TODO*/ },
            )
            var password = rememberSaveable {
                mutableStateOf("")
            }
            var passwordVisibility by remember {
                mutableStateOf(false)
            }
            var passwordError by remember { mutableStateOf(false) }
            com.kodea.FixMaster.presentation.authentification.common.OutlinedTextField(
                value = password,
                placeHolder = "Password",
                label = "Password",
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password),
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (!passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (!passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, top = 8.dp), contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = stringResource(id = R.string.ForgotPassword),
                    textAlign = TextAlign.Right,
                    modifier = Modifier.clickable {
                        //navController.navigate(Route.PasswordReset.route)
                        if (permissions.all {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    it
                                ) == PackageManager.PERMISSION_GRANTED
                            }) {
                            //get Location
                            scope.launch {
                                locationInfo = locationClient.getCurrentLocation(
                                    Priority.PRIORITY_HIGH_ACCURACY,
                                    CancellationTokenSource().token
                                ).await()
                            }


                            Toast.makeText(
                                context,
                                "${locationInfo?.latitude} and ${locationInfo?.longitude}",
                                Toast.LENGTH_LONG
                            ).show()

                        } else launcherMultiplePermissions.launch(permissions)
                    })
            }

            Button(
                onClick = {
                    /*if (phoneNumber.isEmpty()) {
                        phoneNumberError = true
                    } else {
                        if (phoneNumber.length != 10) phoneNumberError = true
                    }
                    if (password.isEmpty()) {
                        passwordError = true
                    } else {
                        if (password.length < 8) passwordError = true
                        else {
                            passwordError = false
                        }
                    }
                    if (phoneNumberError == false && passwordError == false) {
                        navController.popBackStack()
                        navController.navigate(Route.BrowseNavigation.route)
                    }*/
                    navController.navigate(Route.BrowseNavigation.route)

                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.SignIn))
            }

        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = buildAnnotatedString {
                append(text = stringResource(id = R.string.DontHaveAccount))
                append(text = " ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append(text = stringResource(id = R.string.SignUp))
                }
            }, textAlign = TextAlign.Center, modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable {
                    navController.navigate(AuthScreens.SignUpScreen.route + "/${locationInfo?.latitude}/${locationInfo?.longitude}")
                })
        }


    }
}
/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun test() {
    val navController = rememberNavController()
    SignInScreen(navController = navController)
}*/
