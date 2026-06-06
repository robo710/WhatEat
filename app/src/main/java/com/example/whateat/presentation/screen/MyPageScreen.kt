package com.example.whateat.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@Composable
fun MyPageScreen(modifier: Modifier = Modifier) {
    Text(text = "여기는 유저 프로필 및 정보 수정 공간이에요! 👤", modifier = modifier.padding(24.dp))
}

@DevicePreviews
@Composable
fun MyPageScreenPreview() {
    WhatEatTheme {
        MyPageScreen()
    }
}