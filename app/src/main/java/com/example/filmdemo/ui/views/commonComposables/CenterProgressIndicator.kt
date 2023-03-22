package com.example.filmdemo.ui.views.commonComposables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun CenterProgressIndicator(modifier: Modifier) {
    Box(
        modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = Color.Red)
    }
}