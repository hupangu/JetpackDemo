package com.leo.demo.jetpack

import androidx.compose.runtime.Composable
import com.leo.demo.jetpack.base.BaseActivity
import com.leo.demo.jetpack.navigation.MainScreen

class MainActivity : BaseActivity() {
    @Composable
    override fun setupViews() {
        MainScreen()
    }
}

