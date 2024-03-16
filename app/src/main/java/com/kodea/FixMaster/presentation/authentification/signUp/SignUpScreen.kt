package com.kodea.FixMaster.presentation.authentification.signUp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kodea.FixMaster.R
import com.kodea.FixMaster.presentation.authentification.common.OutlinedTextField
import com.kodea.FixMaster.util.navigation.Route


@Composable
fun SignUpScreen(navController : NavHostController , viewModel: SignUpViewModel = hiltViewModel()) {


    var text by rememberSaveable {
        mutableStateOf("SignUp Screen")
    }
    Column(modifier = Modifier.fillMaxSize()
        ) {

        /* when (val response = viewModel.data.value) {
             is Response.onLoading -> {
                 //Toast.makeText(LocalContext.current , "loading" ,Toast.LENGTH_LONG).show()
                 CircularProgressIndicator()

             }

             is Response.onSuccess -> {*//*text = "country : ${response.data.addresses[0].address.country}\n" +
                        "wilaya : ${response.data.addresses[0].address.countrySubdivision}\n" +
                        "City : ${response.data.addresses[0].address.municipality}\n" +
                        "ZipCode : ${response.data.addresses[0].address.postalCode}\n" +
                        "Daira : ${response.data.addresses[0].address.countrySecondarySubdivision}"
*//*
                text = "country : ${response.data.display_name}"

                Text(text = text, fontSize = 14.sp)
            }

            is Response.onFaillure -> {
                Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
            }
        }*/

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())

        ) {
            Text(
                text = stringResource(id = R.string.SignUp),
                fontSize = 40.sp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                modifier = Modifier.padding(top = 48.dp)
            )

            Text(
                text = "Create Your Account For Better Experience",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        modifier = Modifier.padding(bottom = 24.dp , top = 12.dp , start = 24.dp , end = 24.dp ),
                textAlign = TextAlign.Center

            )
            var firstName = rememberSaveable {
                mutableStateOf("")
            }

            OutlinedTextField(
                value = firstName,
                placeHolder = "First name",
                label = "First name",
                leadingIcon = { Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null) },
                trailingIcon = { /*TODO*/ },
            )
            var lastName = rememberSaveable {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = lastName,
                placeHolder = "Last name",
                label = "Last name",
                leadingIcon = {  Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null)},
                trailingIcon = { /*TODO*/ })

            var email = rememberSaveable {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = email,
                placeHolder = "Email",
                label = "Email",
                leadingIcon = {  Icon(painter = painterResource(id = R.drawable.email), contentDescription = null)},
                trailingIcon = { /*TODO*/ })
            var phoneNumber = rememberSaveable {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = phoneNumber,
                placeHolder = "Phone number",
                label = "Phone number",
                leadingIcon = {  Icon(painter = painterResource(id = R.drawable.phone), contentDescription = null)},
                trailingIcon = { /*TODO*/ },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone , imeAction = ImeAction.Next)
            )

            var password = rememberSaveable {
                mutableStateOf("")
            }
            var passwordVisibility by remember {
                mutableStateOf(false)
            }
            OutlinedTextField(
                value = password,
                placeHolder = "Password",
                label = "Password",
                leadingIcon = {  Icon(painter = painterResource(id = R.drawable.password), contentDescription = null)},
                trailingIcon = { IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = if (!passwordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility, contentDescription = null)
                } },
                visualTransformation = if (!passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None)

            var confirmPassword = rememberSaveable {
                mutableStateOf("")
            }
            var confirmPasswordVisibility by remember {
                mutableStateOf(false)
            }
            OutlinedTextField(
                value = confirmPassword,
                placeHolder = "Confirm password",
                label = "Confrim password",
                leadingIcon = {  Icon(painter = painterResource(id = R.drawable.password), contentDescription = null)},
                trailingIcon = { IconButton(onClick = { confirmPasswordVisibility = !confirmPasswordVisibility }) {
                    Icon(imageVector = if (!confirmPasswordVisibility) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility, contentDescription = null)
                } },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done ),
                visualTransformation = if (!confirmPasswordVisibility) PasswordVisualTransformation() else VisualTransformation.None,

            )
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
                Text(text = stringResource(id = R.string.SignUp))
            }

        }


    }

}

