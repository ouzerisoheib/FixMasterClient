package com.kodea.FixMaster.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kodea.FixMaster.R
import com.kodea.FixMaster.presentation.home.providerCardData
import com.kodea.FixMaster.ui.theme.PoppinsFontFamily
import com.kodea.FixMaster.util.navigation.Route


@Composable
fun cardProvider(
    item: providerCardData,
    navController: NavHostController
) {


    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary),
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(vertical = 2.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate(Route.ProProfileScreen.route)
            },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Row(modifier = Modifier
            .fillMaxSize()
            ) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(8.dp), contentAlignment = Alignment.TopStart
            ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = null,
                    modifier = Modifier.clip(
                        RoundedCornerShape(8.dp)
                    ),
                    contentScale = ContentScale.Crop
                )
                ElevatedCard(modifier = Modifier.padding(start = 2.dp, top = 2.dp), shape = RoundedCornerShape(8.dp)) {

                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = colorResource(id = R.color.star)
                            )
                            Text(
                                text = item.review,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(end = 2.dp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 4.dp, top = 4.dp), contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        androidx.compose.material.Icon(
                            painter = painterResource(id = R.drawable.outlined_save),
                            contentDescription = "save",
                            tint = MaterialTheme.colorScheme.onBackground
                        )

                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 12.dp), verticalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .weight(1f),
                        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.lightGray))
                    ) {
                        Text(
                            text = item.service,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = item.name,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "location",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = item.location, textAlign = TextAlign.Center)
                    }
                    Text(text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(text = item.price + "DA")
                        }
                        withStyle(SpanStyle(fontWeight = FontWeight.Light)) {
                            append("/${item.priceType}")
                        }
                    })
                }
            }
        }
    }
}