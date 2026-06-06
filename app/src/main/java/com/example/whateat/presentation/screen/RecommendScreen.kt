package com.example.whateat.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecommendScreen(
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // 1. 상태 관리
    var budgetInput by remember { mutableStateOf("") }
    var selectedWeather by remember { mutableStateOf<String?>(null) }
    var selectedMood by remember { mutableStateOf<String?>(null) }
    var dailyNotesInput by remember { mutableStateOf("") }

    val weatherOptions = listOf("☀️ 맑음", "☁️ 흐림", "🌧️ 비", "❄️ 눈")
    // 💡 "🔥 화남" 기분 옵션 추가
    val moodOptions = listOf("🥰 좋음", "😑 평범", "😢 우울", "😮‍💨 피곤", "🔥 화남")

    val PinkPrimary = Color(0xFFFF6B8B)
    val PinkSecondary = Color(0xFFFFF0F2)
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)
    val BorderColor = Color(0xFFF0E4E6)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .padding(horizontal = 24.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 헤더 타이틀
            Column(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    text = "오늘의 한 끼 픽 🎯",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "오늘의 상태를 알려주시면 AI가 더 똑똑하게 골라줘요.",
                    fontSize = 14.sp,
                    color = TextSub
                )
            }

            // ------------------------------------------
            // 2. 예산 직접 입력 섹션
            // ------------------------------------------
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "지출 가능한 예산",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain
                )
                OutlinedTextField(
                    value = budgetInput,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            budgetInput = input
                        }
                    },
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = TextMain,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    placeholder = {
                        Text("예: 12000", color = TextSub.copy(alpha = 0.5f), fontSize = 16.sp)
                    },
                    suffix = {
                        Text(
                            text = "원 이하",
                            fontWeight = FontWeight.Bold,
                            color = PinkPrimary,
                            fontSize = 14.sp
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = BorderColor,
                        cursorColor = PinkPrimary,
                        focusedTextColor = TextMain,
                        unfocusedTextColor = TextMain
                    ),
                    singleLine = true
                )
            }

            // ------------------------------------------
            // 3. 오늘 날씨 선택 섹션 (Optional)
            // ------------------------------------------
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "오늘 날씨는 어떤가요? (선택)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    weatherOptions.forEach { weather ->
                        val isSelected = selectedWeather == weather
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) PinkPrimary else PinkSecondary.copy(alpha = 0.5f))
                                .clickable {
                                    focusManager.clearFocus()
                                    selectedWeather = if (isSelected) null else weather
                                }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = weather,
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else TextMain
                            )
                        }
                    }
                }
            }

            // ------------------------------------------
            // 4. 현재 기분 선택 섹션 (Optional)
            // ------------------------------------------
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "지금 기분은 어떠세요? (선택)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain
                )

                // 💡 Row 대신 FlowRow를 사용하여 기분이 5개로 늘어나도 기기 크기에 맞춰 예쁘게 줄바꿈되도록 처리
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 3 // 한 줄에 최대 3개씩 균형감 있게 배치
                ) {
                    moodOptions.forEach { mood ->
                        val isSelected = selectedMood == mood
                        Box(
                            modifier = Modifier
                                // FlowRow 내에서 고정 너비 비율 분할을 위해 명시적 처리 대신
                                // 유동적인 패딩 스타일을 취하거나, 한 행당 3개 균등 정렬을 위해 0.31f 가중치 부여 가능
                                .fillMaxWidth(0.30f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) PinkPrimary else PinkSecondary.copy(alpha = 0.5f))
                                .clickable {
                                    focusManager.clearFocus()
                                    selectedMood = if (isSelected) null else mood
                                }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = mood,
                                fontSize = 14.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else TextMain
                            )
                        }
                    }
                }
            }

            // ------------------------------------------
            // 5. 오늘의 입맛/특이사항 섹션 (Optional)
            // ------------------------------------------
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "오늘따라 이런 게 당기네 (선택)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain
                )
                OutlinedTextField(
                    value = dailyNotesInput,
                    onValueChange = { dailyNotesInput = it },
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = TextMain,
                        fontSize = 15.sp
                    ),
                    placeholder = {
                        Text(
                            text = "예: 매콤한 게 당김, 밀가루는 싫어요, 일식이 좋음",
                            color = TextSub.copy(alpha = 0.5f),
                            fontSize = 14.sp
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = BorderColor,
                        cursorColor = PinkPrimary,
                        focusedTextColor = TextMain,
                        unfocusedTextColor = TextMain
                    ),
                    singleLine = true
                )
            }

            Divider(color = BorderColor)

            // 6. 대기 상태 카드
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 10.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = PinkSecondary),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "🤔", fontSize = 32.sp)
                    Text(
                        text = "조건을 입력하고 아래 버튼을 누르면\nAI 비서가 메뉴를 뚝딱 추천해 드려요!",
                        fontSize = 13.sp,
                        color = TextSub,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // 7. 하단 고정 추천받기 버튼 영역
        Button(
            onClick = {
                focusManager.clearFocus()
                keyboardController?.hide()
                // TODO: budgetInput, selectedWeather, selectedMood, dailyNotesInput 취합하여 AI API 연동
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary)
        ) {
            Text(
                text = "🔥 오늘의 메뉴 추천받기",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@DevicePreviews
@Composable
fun RecommendScreenPreview() {
    WhatEatTheme {
        RecommendScreen()
    }
}