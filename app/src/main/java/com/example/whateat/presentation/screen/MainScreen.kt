package com.example.whateat.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "메인화면",
            fontSize = 15.sp
        )
    }
}

@DevicePreviews
@Composable
fun MainScreenPreview(){
    WhatEatTheme {
        MainScreen()
    }
}