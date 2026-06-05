package com.example.whateat.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendScreen(
    modifier: Modifier = Modifier
) {
    // 1. 상태 관리
    var budgetInput by remember { mutableStateOf("") } // 예산 직접 입력 값
    var selectedWeather by remember { mutableStateOf<String?>(null) } // 선택된 날씨 (Optional)
    var selectedMood by remember { mutableStateOf<String?>(null) } // 선택된 기분 (Optional)

    val weatherOptions = listOf("☀️ 맑음", "☁️ 흐림", "🌧️ 비", "❄️ 눈")
    val moodOptions = listOf("🥰 좋음", "😑 평범", "😢 우울", "😮‍💨 피곤")

    // 🎨 일관된 러블리 핑크 감성 테마 컬러
    val PinkPrimary = Color(0xFFFF6B8B)
    val PinkSecondary = Color(0xFFFFF0F2)
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)
    val BorderColor = Color(0xFFF0E4E6)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .systemBarsPadding()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 상단 스크롤 가능한 메인 컨텐츠 영역
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
            // 2. 예산 직접 입력 섹션 (슬라이더 대체)
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
                        // 숫자만 입력 가능하도록 필터링
                        if (input.all { it.isDigit() }) {
                            budgetInput = input
                        }
                    },
                    placeholder = { Text("예: 12000", color = TextSub.copy(alpha = 0.6f)) },
                    suffix = { Text("원 이하", fontWeight = FontWeight.Bold, color = TextMain) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        unfocusedBorderColor = BorderColor,
                        cursorColor = PinkPrimary
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
                                    // 이미 선택된 걸 또 누르면 선택 해제(Optional), 아니면 새롭게 선택
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    moodOptions.forEach { mood ->
                        val isSelected = selectedMood == mood
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) PinkPrimary else PinkSecondary.copy(alpha = 0.5f))
                                .clickable {
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

            Divider(color = BorderColor)

            // 5. 대기 상태 카드
            Card(
                modifier = Modifier.fillMaxWidth(),
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

        // 6. 하단 고정 추천받기 버튼 영역
        Button(
            onClick = {
                // TODO: budgetInput, selectedWeather, selectedMood 데이터를 실시간 취합하여 AI 비서 호출 예정
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