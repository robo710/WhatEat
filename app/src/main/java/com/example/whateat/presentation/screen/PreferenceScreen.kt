package com.example.whateat.presentation.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceScreen() {
    // 1. 음식 카테고리 상태 (한식, 일식, 중식, 양식, 패스트푸드)
    val categories = listOf("한식", "일식", "중식", "양식", "패스트푸드")
    val selectedCategories = remember { mutableStateListOf<String>() }

    // 🔥 3. 유저가 직접 입력할 기타 특이사항 상태 추가
    var customNotes by remember { mutableStateOf("") }

    // 스크롤이 가능하도록 설정
    Column (
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp, vertical = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // 타이틀 영역
        Column {
            Text(
                text = "반가워요! 🍽️",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "평소 입맛과 특이사항을 알려주시면 AI가 예산에 맞춰 딱 맞는 메뉴를 찾아드릴게요.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 22.sp
            )
        }

        Divider()

        // 섹션 1: 선호 카테고리 (체크박스 형태)
        Column {
            Text(
                text = "선호하는 음식 종류",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))

            categories.forEach { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 1.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedCategories.contains(category),
                        onCheckedChange = { isChecked ->
                            if (isChecked) selectedCategories.add(category)
                            else selectedCategories.remove(category)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = category, fontSize = 16.sp)
                }
            }
        }

        // 🔥 섹션 3: 주관식 특이사항 메모란 추가
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "기타 특이사항 및 메모",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = customNotes,
                onValueChange = { customNotes = it },
                placeholder = {
                    Text(
                        text = "예: 오이나 당근은 못 먹어요, 다이어트 중이라 닭가슴살이나 샐러드 위주로 원합니다, 자극적인 음식을 선호합니다..",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp), // 적당히 여러 줄 입력할 수 있는 높이
                maxLines = 5
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 저장하기 버튼
        Button(
            onClick = {
                // 상단 선택 값들과 주관식 텍스트를 한 번에 확인 가능
                Log.d(TAG, "선택된 카테고리: $selectedCategories")
                Log.d(TAG, "입력된 특이사항: $customNotes")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "내 취향 저장하고 시작하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@DevicePreviews
@Composable
fun PreferenceScreenPreview(){
    WhatEatTheme {
        PreferenceScreen()
    }
}