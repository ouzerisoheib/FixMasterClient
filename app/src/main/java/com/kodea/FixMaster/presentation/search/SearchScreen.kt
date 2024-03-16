package com.kodea.FixMaster.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kodea.FixMaster.R
import com.kodea.FixMaster.presentation.common.cardProvider
import com.kodea.FixMaster.presentation.home.providerCardData

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController) {
    /*Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
        Text(text = "Search screen")

    }*/

    var text by rememberSaveable {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }

    var lists = listOf<providerCardData>(
        providerCardData(R.drawable.painterprofile, "Philipp lackner", "algiers,Algeria", "painter", "250", "4.7", "32", "hr"),
        providerCardData(R.drawable.painterprofile, "Philipp lackner", "algiers,Algeria", "painter", "250", "4.7", "32", "hr"),
        providerCardData(R.drawable.painterprofile, "Philipp lackner", "algiers,Algeria", "painter", "250", "4.7", "32", "hr"),
        providerCardData(R.drawable.painterprofile, "Philipp lackner", "algiers,Algeria", "painter", "250", "4.7", "32", "hr"),
        providerCardData(R.drawable.painterprofile, "Philipp lackner", "algiers,Algeria", "painter", "250", "4.7", "32", "hr"),
        providerCardData(R.drawable.painterprofile, "Philipp lackner", "algiers,Algeria", "painter", "250", "4.7", "32", "hr"),
        providerCardData(R.drawable.painterprofile, "Philipp lackner", "algiers,Algeria", "painter", "250", "4.7", "32", "hr"),
    )

    Scaffold {
        SearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = { active = !active },
            active = active,
            onActiveChange = { active = it },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.filled_search),
                    contentDescription = "Search"
                )
            },
            shape = ShapeDefaults.Medium,
            trailingIcon = {
                if (!active)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "Filter"
                        )
                    }
                else IconButton(onClick = {
                    if (text.isEmpty()) {
                        active = false
                    } else text = ""
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
            LazyColumn{
                items(lists){item ->  
                    cardProvider(item = item , navController)
                }
            }
        }
    }
}