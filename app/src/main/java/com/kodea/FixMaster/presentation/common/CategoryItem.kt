package com.kodea.FixMaster.presentation.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kodea.FixMaster.R

@Composable
fun CategoryItem(navController: NavHostController, Modifier : Modifier, title: String, icon: String, desc: String) {

    Column(
        modifier = Modifier
            .width(56.dp)
            .height(72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            backgroundColor = colorResource(id = R.color.gris),
            shape = CircleShape,
            modifier = Modifier.size(48.dp)
        ) {
            AsyncImage(
                model = icon,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)

            )

        }
        Text(
            text = title,
            color = if (!isSystemInDarkTheme()) Color.Black else Color.White,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.W500
        )

    }


}