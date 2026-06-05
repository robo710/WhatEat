package com.example.whateat.presentation.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun PreferenceScreen() {
    // 1. 상태 및 단계 관리
    var currentStep by remember { mutableStateOf(1) } // 1: 시작, 2: 카테고리, 3: 특이사항

    val categories = listOf("한식", "일식", "중식", "양식", "패스트푸드")
    val selectedCategories = remember { mutableStateListOf<String>() }
    var customNotes by remember { mutableStateOf("") }

    // 🎨 러블리 핑크 감성 테마 컬러 정의
    val PinkPrimary = Color(0xFFFF6B8B)
    val PinkSecondary = Color(0xFFFFF0F2)
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)
    val BorderColor = Color(0xFFF0E4E6)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween // 상단 컨텐츠와 하단 버튼을 위아래로 밀어줌
    ) {

        // 2. 단계별 컨텐츠 영역 (전환 시 부드러운 애니메이션 적용)
        AnimatedContent(
            targetState = currentStep,
            transitionSpec = { fadeIn() with fadeOut() },
            modifier = Modifier.weight(1f)
        ) { step ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                when (step) {
                    // ==========================================
                    // 1단계: 웰컴 시작 화면
                    // ==========================================
                    1 -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 80.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "WhatEat 🍽️",
                                fontSize = 42.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = PinkPrimary
                            )
                            Text(
                                text = "오늘 뭐 먹지?",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextMain
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "결정 장애를 앓고 있는 당신을 위해\nAI 비서가 완벽한 한 끼를 골라드릴게요.",
                                fontSize = 16.sp,
                                color = TextSub,
                                textAlign = TextAlign.Center,
                                lineHeight = 26.sp
                            )
                        }
                    }

                    // ==========================================
                    // 2단계: 음식 카테고리 선택 화면
                    // ==========================================
                    2 -> {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Column {
                                Text(
                                    text = "선호하는 맛 탐색 🔍",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PinkPrimary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "평소에 자주 먹거나 선호하는 음식 카테고리를 체크해 주세요.",
                                    fontSize = 14.sp,
                                    color = TextSub
                                )
                            }

                            Divider(color = BorderColor)

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = PinkSecondary)
                            ) {
                                Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)) {
                                    categories.forEach { category ->
                                        val isChecked = selectedCategories.contains(category)
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(12.dp))
                                                .clickable {
                                                    if (isChecked) selectedCategories.remove(category)
                                                    else selectedCategories.add(category)
                                                }
                                                .padding(horizontal = 16.dp, vertical = 10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Checkbox(
                                                checked = isChecked,
                                                onCheckedChange = { checked ->
                                                    if (checked) selectedCategories.add(category)
                                                    else selectedCategories.remove(category)
                                                },
                                                colors = CheckboxDefaults.colors(
                                                    checkedColor = PinkPrimary,
                                                    uncheckedColor = TextSub.copy(alpha = 0.5f),
                                                    checkmarkColor = Color.White
                                                )
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = category,
                                                fontSize = 16.sp,
                                                fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal,
                                                color = if (isChecked) PinkPrimary else TextMain
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // ==========================================
                    // 3단계: 기타 세부 특이사항 작성 화면
                    // ==========================================
                    3 -> {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Column {
                                Text(
                                    text = "나만의 입맛 디테일 ✍️",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PinkPrimary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "AI가 거를 수 있도록 못 먹는 음식, 알레르기, 혹은 다이어트 등의 특이사항을 적어주세요.",
                                    fontSize = 14.sp,
                                    color = TextSub
                                )
                            }

                            Divider(color = BorderColor)

                            OutlinedTextField(
                                value = customNotes,
                                onValueChange = { customNotes = it },
                                placeholder = {
                                    Text(
                                        text = "예: 오이나 당근은 못 먹어요, 다이어트 중이라 닭가슴살 위주로 추천 원해요, 자극적이고 매운 음식을 선호합니다.",
                                        fontSize = 14.sp,
                                        color = TextSub.copy(alpha = 0.6f),
                                        lineHeight = 22.sp
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(16.dp + 160.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = PinkPrimary,
                                    unfocusedBorderColor = BorderColor,
                                    cursorColor = PinkPrimary
                                ),
                                maxLines = 8
                            )
                        }
                    }
                }
            }
        }

        // 3. 하단 다이내믹 액션 버튼 영역
        Column(modifier = Modifier.fillMaxWidth()) {
            if (currentStep > 2) {
                Button(
                    onClick = { currentStep-- },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "이전 단계로", color = TextSub, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // 메인 진행 버튼 (가장 밑에 안정적으로 배치)
            Button(
                onClick = {
                    when (currentStep) {
                        1 -> currentStep = 2 // 시작 ➔ 카테고리 이동
                        2 -> currentStep = 3 // 카테고리 ➔ 특이사항 이동
                        3 -> {
                            // 최종 완료 및 저장 단계
                            Log.d(TAG, "최종 선택 카테고리: $selectedCategories")
                            Log.d(TAG, "최종 작성 특이사항: $customNotes")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary)
            ) {
                Text(
                    text = when (currentStep) {
                        1 -> "오늘의 메뉴 추천 앱 시작하기"
                        2 -> "다음 단계로 가기"
                        else -> "내 취향 저장하고 시작하기"
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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