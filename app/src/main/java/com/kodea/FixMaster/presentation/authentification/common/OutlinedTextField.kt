package com.kodea.FixMaster.presentation.authentification.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTextField(
    value: MutableState<String>,
    placeHolder: String,
    label: String,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    supportingText: @Composable () -> Unit = {}
) {

    OutlinedTextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp, end = 8.dp
            ),
        placeholder = {
            Text(
                text = placeHolder
            )
        },
        leadingIcon = leadingIcon,

        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        label = { Text(text = label) },
        shape = RoundedCornerShape(8.dp),
        visualTransformation = visualTransformation,
        isError = isError,
        singleLine = true,
        maxLines = 1,
        supportingText = supportingText,

    )
}