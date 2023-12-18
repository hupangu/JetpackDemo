package com.leo.demo.jetpack.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.leo.demo.jetpack.ui.base.ScreenPlaceHolder

const val CATEGORY_SCREEN_ROUTE = "category"

@Composable
fun CategoryScreen() {
    ScreenPlaceHolder(name = "Category", background = Color.Green)
}
