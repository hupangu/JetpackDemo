package com.leo.demo.jetpack.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.leo.demo.jetpack.ui.base.ScreenPlaceHolder

const val ME_SCREEN_ROUTE = "me"

@Composable
fun MeScreen() {
    ScreenPlaceHolder(name = "Me", background = Color.Red)
}
