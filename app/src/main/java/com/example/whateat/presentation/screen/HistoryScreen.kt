package com.example.whateat.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    Text(text = "여기는 나의 기록이 들어올 공간이에요! 🍕", modifier = modifier.padding(24.dp))
}

@DevicePreviews
@Composable
fun HistoryScreenPreview() {
    WhatEatTheme {
        HistoryScreen()
    }
}