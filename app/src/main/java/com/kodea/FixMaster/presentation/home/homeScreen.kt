package com.kodea.FixMaster.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kodea.FixMaster.R
import com.kodea.FixMaster.data.repository.Category
import com.kodea.FixMaster.presentation.common.CategoryItem
import com.kodea.FixMaster.presentation.common.cardProvider
import com.kodea.FixMaster.ui.theme.PoppinsFontFamily
import com.kodea.FixMaster.util.Response
import com.kodea.FixMaster.util.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    var lists: MutableList<Category> = remember { mutableListOf() }
    val listsCardProvider = listOf(
        providerCardData(
            R.drawable.painterprofile,
            "Philipp lackner",
            "algiers,Algeria",
            "painter",
            "250",
            "4.7",
            "32",
            "hr"
        ),
        providerCardData(
            R.drawable.painterprofile,
            "Philipp lackner",
            "algiers,Algeria",
            "painter",
            "250",
            "4.7",
            "32",
            "hr"
        ),
        providerCardData(
            R.drawable.painterprofile,
            "Philipp lackner",
            "algiers,Algeria",
            "painter",
            "250",
            "4.7",
            "32",
            "hr"
        ),
        providerCardData(
            R.drawable.painterprofile,
            "Philipp lackner",
            "algiers,Algeria",
            "painter",
            "250",
            "4.7",
            "32",
            "hr"
        ),
        providerCardData(
            R.drawable.painterprofile,
            "Philipp lackner",
            "algiers,Algeria",
            "painter",
            "250",
            "4.7",
            "32",
            "hr"
        ),
    )
    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    Scaffold(modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "FixMaster",
                        fontFamily = PoppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Route.NotificationScreen.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification),
                            contentDescription = "Notification"
                        )
                    }
                }, backgroundColor = MaterialTheme.colorScheme.background
            )
        }) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else colorResource(
                        id = R.color.lightGray
                    )
                )
        ) {

            item {
                /*SearchBar(
                    query = searchText,
                    onQueryChange = { searchText = it },
                    onSearch = { active = !active },
                    active = active,
                    onActiveChange = { active = it },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
                    },
                    shape = ShapeDefaults.Medium,
                    trailingIcon = {
                        if (!active)
                            IconButton(onClick = { *//*TODO*//* }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.settings),
                                    contentDescription = "filter"
                                )
                            }
                        else IconButton(onClick = {
                            if (searchText.isEmpty()) {
                                active = false
                            } else searchText = ""
                        }) {
                            Icon(imageVector = Icons.Outlined.Close, contentDescription = "close")
                        }
                    },
                    placeholder = { Text(text = "Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                )
                {
                }
                Spacer(modifier = Modifier.height(4.dp))*/
            }
            item {
                HeaderRow(navController = navController, title = "#SpecialForYou", onClick = {})
                SliderBanner(navController = navController)
            }
            item {
                HeaderRow(navController = navController, title = "Categories", onClick = {
                    navController.navigate(Route.CategoryListScreen.route)
                })

                when (val resp = viewModel.categoryList.value) {
                    is Response.onLoading -> {
                    }

                    is Response.onFaillure -> {
                        Toast.makeText(LocalContext.current, resp.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    is Response.onSuccess -> {
                        //if (resp.data.isNotEmpty()) title = resp.data[0]?.title ?: ""

                        if (resp.data.isNotEmpty()) lists = resp.data as MutableList<Category>

                        //CategoryRow()
                        if (lists.isNotEmpty()) {

                            Row(modifier = Modifier.fillMaxWidth()) {
                                lists.forEach {
                                    CategoryItem(
                                        navController = navController,
                                        Modifier = Modifier.weight(1f),
                                        title = it.title,
                                        icon = it.icon,
                                        desc = it.description
                                    )
                                }
                            }
                        }


                    }
                }
            }
            item {
                HeaderRow(navController = navController, title = "Best Performers", onClick = {})
            }
            //popularServices()
            items(listsCardProvider) { item ->
                cardProvider(item, navController)
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun SliderBanner(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val pagerState = rememberPagerState(initialPage = 0)



    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column {
        HorizontalPager(
            count = 3,
            state = pagerState,

            modifier = modifier
                .height(126.dp)
                .fillMaxWidth()
        ) { index ->
            cardProvider(item = lists[index], navController = navController)
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp), activeColor = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun HeaderRow(
    navController: NavHostController,
    title: String,
    onClick: (navController: NavHostController) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "See all",
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable {
                    //navController.navigate(Route.CategoryListScreen.route)
                    onClick(navController)
                },
            color = Color.Blue,
            fontSize = 14.sp,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Light,
        )
    }
}

data class providerCardData(
    val image: Int,
    val name: String,
    val location: String,
    val service: String,
    val price: String,
    val review: String,
    val nbReview: String,
    val priceType: String
)

val lists = listOf(
    providerCardData(
        R.drawable.painterprofile,
        "Philipp lackner",
        "algiers,Algeria",
        "painter",
        "250",
        "4.7",
        "32",
        "hr"
    ),
    providerCardData(
        R.drawable.painterprofile,
        "Philipp lackner",
        "algiers,Algeria",
        "painter",
        "250",
        "4.7",
        "32",
        "hr"
    ),
    providerCardData(
        R.drawable.painterprofile,
        "Philipp lackner",
        "algiers,Algeria",
        "painter",
        "250",
        "4.7",
        "32",
        "hr"
    ),
    providerCardData(
        R.drawable.painterprofile,
        "Philipp lackner",
        "algiers,Algeria",
        "painter",
        "250",
        "4.7",
        "32",
        "hr"
    ),
    providerCardData(
        R.drawable.painterprofile,
        "Philipp lackner",
        "algiers,Algeria",
        "painter",
        "250",
        "4.7",
        "32",
        "hr"
    ),
)