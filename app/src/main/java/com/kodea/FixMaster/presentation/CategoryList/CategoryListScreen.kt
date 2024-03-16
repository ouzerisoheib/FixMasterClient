package com.kodea.FixMaster.presentation.CategoryList

import android.widget.Toast
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.BasicTooltipDefaults
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kodea.FixMaster.R
import com.kodea.FixMaster.data.repository.Category
import com.kodea.FixMaster.presentation.common.CategoryItem
import com.kodea.FixMaster.presentation.common.shimmerEffect
import com.kodea.FixMaster.ui.theme.PoppinsFontFamily
import com.kodea.FixMaster.util.Response
import com.kodea.FixMaster.util.navigation.Route


@Composable
fun CategoryListScreen(
    navController: NavHostController,
    catViewModel: CategoryListViewModel = hiltViewModel()
) {

    var lists: MutableList<Category> = remember { mutableListOf() }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Category",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }, backgroundColor = MaterialTheme.colorScheme.background
        )
    }) {paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (val resp = catViewModel.categoryList.value) {
                is Response.onLoading -> {
                }

                is Response.onFaillure -> {
                    Toast.makeText(LocalContext.current, resp.message, Toast.LENGTH_SHORT).show()
                }

                is Response.onSuccess -> {
                    //if (resp.data.isNotEmpty()) title = resp.data[0]?.title ?: ""

                    if (resp.data.isNotEmpty()) lists = resp.data as MutableList<Category>

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        modifier = Modifier.padding(top = 8.dp)

                    ) {
                        items(lists) { item ->
                            CategoryItem(navController,Modifier ,item.title, item.icon, item.description)
                        }
                    }

                }
            }



        }
    }

}

data class CategoryItemData(
    val icon: Int,
    val title: String
)



