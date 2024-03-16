package com.kodea.FixMaster.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.kodea.FixMaster.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page("Quick Search", "Lorem Ipsum is simply dummy text of the printing and typesetting industry", R.drawable.search_illust),
    Page("Well Done!!", "Lorem Ipsum is simply dummy text of the printing and typesetting industry", R.drawable.done_illust),
    Page("Review", "Lorem Ipsum is simply dummy text of the printing and typesetting industry", R.drawable.review_illust)

)