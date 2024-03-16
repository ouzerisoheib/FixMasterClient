package com.kodea.FixMaster.presentation.proProfile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kodea.FixMaster.R
import com.kodea.FixMaster.presentation.home.providerCardData
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.kodea.FixMaster.presentation.proProfile.DetailsReviews.DetailsReviewsTabScreen
import com.kodea.FixMaster.presentation.proProfile.Gallery.GalleryTabScreen
import com.kodea.FixMaster.presentation.proProfile.Profile.ProfileTabScreen
import com.kodea.FixMaster.ui.theme.PoppinsFontFamily

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class
)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProProfileScreen(navController: NavHostController = rememberNavController()) {
    val providerCardData = providerCardData(
        R.drawable.painterprofile,
        "Philipp lackner",
        "algiers,Algeria",
        "painter",
        "250",
        "4.7",
        "32",
        "hr"
    )
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Profile", "Details reviews", "Gallery")
    val pagerState = rememberPagerState(initialPage = 0) {
        titles.size
    }

    LaunchedEffect(key1 = state) {
        pagerState.animateScrollToPage(state)
    }
    LaunchedEffect(key1 = pagerState.currentPage) {
        state = pagerState.currentPage
    }
    var openConfirmDialog by remember {
        mutableStateOf(false)
    }
    when(openConfirmDialog){
        true -> ConfirmDialog(
            onDismissRequest = { openConfirmDialog = false },
            onConfirmation = { openConfirmDialog = false },
            dialogTitle = "Confirmation",
            dialogText = "do you confirm the hire with you current location ?",
            icon = Icons.Filled.Check
        )
        else -> true
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pro Profile",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }, backgroundColor = MaterialTheme.colorScheme.background,
                navigationIcon = { IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = "Back")
                }}
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                          openConfirmDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
                containerColor = colorResource(id = R.color.blue),
                shape = RoundedCornerShape(32.dp),
                

            ) {
                Text(
                    text = stringResource(id = R.string.HireNow ),
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 22.sp
                )

            }
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.Center

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            proCard(item = providerCardData)

            SecondaryTabRow(selectedTabIndex = state) {
                titles.forEachIndexed { index, title ->
                    Tab(selected = state == index, onClick = { state = index },
                        text = { Text(text = title) })
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (state) {
                    0 -> ProfileTabScreen()
                    1 -> DetailsReviewsTabScreen()
                    2 -> GalleryTabScreen()
                }
            }

        }
    }
}

@Composable
fun proCard(
    item: providerCardData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
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
                    Icon(
                        painter = painterResource(id = R.drawable.outlined_save),
                        contentDescription = "save"
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
                        color = colorResource(id = R.color.blue)
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
                        tint = colorResource(
                            id = R.color.blue
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = item.location, textAlign = TextAlign.Center)
                }
                Text(text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = colorResource(id = R.color.blue),
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
