package com.leo.demo.jetpack.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.leo.demo.jetpack.ui.theme.JetpackDemoTheme

@Composable
fun ScreenPlaceHolder(name: String, background: Color? = null) {
    JetpackDemoTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background ?: MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
