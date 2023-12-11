package com.leo.demo.jetpack.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.leo.demo.jetpack.ui.theme.JetpackTheme

open class BaseActivity : ComponentActivity() {
    protected val tag = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setupViews()
                }
            }
        }
    }

    @Composable
    protected open fun setupViews() {
        Box {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = tag
            )
        }
    }
}
