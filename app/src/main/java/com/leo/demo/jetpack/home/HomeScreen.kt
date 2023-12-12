package com.leo.demo.jetpack.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(controller: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Home")
    }
}
