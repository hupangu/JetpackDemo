package com.leo.demo.jetpack.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.leo.demo.jetpack.ui.base.ScreenPlaceHolder

const val SHOPPING_SCREEN_ROUTE = "shopping"

@Composable
fun ShopCarScreen() {
    ScreenPlaceHolder(name = "Shopping", background = Color.Blue)
}
