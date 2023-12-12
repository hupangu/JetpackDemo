package com.leo.demo.jetpack.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProductDetailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Product Detail")
    }
}
