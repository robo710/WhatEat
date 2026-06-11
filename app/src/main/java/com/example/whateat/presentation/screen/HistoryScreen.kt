package com.example.whateat.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

// 기록 데이터 표현을 위한 데이터 클래스
data class EatHistory(
    val id: String,
    val date: String,
    val menuName: String,
    val restaurantName: String,
    val mood: String,
    val spentMoney: Int,
    val budgetLimit: Int
)

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier
) {
    // 🎨 테마 컬러 매칭
    val PinkPrimary = Color(0xFFFF6B8B)
    val PinkSecondary = Color(0xFFFFF0F2)
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)
    val BorderColor = Color(0xFFF0E4E6)
    val MoneyGreen = Color(0xFF2EC4B6)

    // 가상의 이번 달 목표 및 지출 데이터 (대시보드용)
    val monthlyBudget = 300000
    val monthlySpent = 184000
    val savedMoney = 46000 // 예산 대비 아낀 금액 합산
    val progress = monthlySpent.toFloat() / monthlyBudget.toFloat()

    // 먹방 기록 타임라인 더미 데이터
    val historyList = listOf(
        EatHistory("1", "6월 8일 점심", "🔥 매운 치즈 불닭발", "홍대 화끈한 불닭발 본점", "🔥 화남", 14000, 15000),
        EatHistory("2", "6월 7일 저녁", "🥰 연어 초밥 정식", "미도인 홍대", "🥰 좋음", 13500, 15000),
        EatHistory("3", "6월 5일 점심", "😮‍💨 돈까스 카레", "고치소우 카레", "😮‍💨 피곤", 9500, 12000),
        EatHistory("4", "6월 3일 저녁", "😑 짜장면과 군만두", "홍콩반점", "😑 평범", 7500, 10000)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFF9F5F6)) // 일관성 있는 그레이핑크 배경
    ) {
        // 상단 타이틀 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 12.dp)
        ) {
            Text(
                text = "나의 먹방 기록 📝",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "내가 먹은 음식들과 절약한 예산을 모아봐요.",
                fontSize = 13.sp,
                color = TextSub
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ------------------------------------------
            // 1. 이번 달 지출 리포트 대시보드 카드
            // ------------------------------------------
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "6월 식비 리포트 📊",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextMain
                        )

                        // 금액 표시부
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Column {
                                Text(text = "이번 달 사용 금액", fontSize = 12.sp, color = TextSub)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = String.format("%,d원", monthlySpent),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = TextMain
                                )
                            }
                            Text(
                                text = String.format("목표 %,d원", monthlyBudget),
                                fontSize = 13.sp,
                                color = TextSub,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        // 게이지 바
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = PinkPrimary,
                            trackColor = PinkSecondary
                        )

                        // 아낀 금액 알림 영역
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(PinkSecondary.copy(alpha = 0.5f))
                                .padding(vertical = 10.dp, horizontal = 14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "💡 설정 예산 대비 아낀 금액", fontSize = 13.sp, color = TextMain)
                                Text(
                                    text = String.format("+%,d원", savedMoney),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MoneyGreen
                                )
                            }
                        }
                    }
                }
            }

            // 섹션 구분 타이틀
            item {
                Text(
                    text = "타임라인 ⏳",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextMain,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }

            // ------------------------------------------
            // 2. 날짜별 히스토리 타임라인 리스트
            // ------------------------------------------
            items(historyList) { history ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 좌측: 기분 아이콘 원형 배치
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    if (history.mood == "🔥 화남") Color(0xFFFFEAEA) else PinkSecondary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = history.mood.split(" ").lastOrNull() ?: "🤔",
                                fontSize = 22.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // 우측: 텍스트 정보 상세 데이터
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = history.date,
                                    fontSize = 11.sp,
                                    color = TextSub,
                                    fontWeight = FontWeight.Medium
                                )
                                // 내 예산 쉴드 비율 간략히 텍스트 표시
                                Text(
                                    text = "예산 ${String.format("%,d원", history.budgetLimit)} 이하",
                                    fontSize = 11.sp,
                                    color = TextSub
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = history.menuName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextMain
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = history.restaurantName,
                                    fontSize = 13.sp,
                                    color = TextSub
                                )
                                // 실제 지출액 강조
                                Text(
                                    text = String.format("%,d원", history.spentMoney),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PinkPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
fun HistoryScreenPreview() {
    WhatEatTheme {
        HistoryScreen()
    }
}