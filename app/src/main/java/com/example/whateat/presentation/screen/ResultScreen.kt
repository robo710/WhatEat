package com.example.whateat.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

// 맛집 정보 표현을 위한 더미 데이터 구조
data class Restaurant(
    val id: String,
    val name: String,
    val category: String,
    val distance: String,
    val address: String,
    val rating: Double,
    val reviewCount: Int,
    val isBookmarked: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    onBackClick: () -> Unit = {},
    onRestaurantClick: (Restaurant) -> Unit = {}
) {
    // 🎨 테마 컬러 레이아웃 매칭
    val PinkPrimary = Color(0xFFFF6B8B)
    val PinkSecondary = Color(0xFFFFF0F2)
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)
    val RatingColor = Color(0xFFFFB800)

    // 가상의 AI 추천 결과 세팅 (예: 화남 + 15000원 이하 조건)
    val recommendedMenu = "🔥 매운 치즈 불닭발"
    val aiComment = "오늘 하루 정말 고생 많으셨어요! 머리끝까지 오른 화를 잠재우기엔 화끈하게 매운 불닭발에 고소한 치즈 조합이 직약입니다. 땀 한 방울 흘리면서 스트레스를 날려버리세요!"

    // 주변 식당 리스트 더미 데이터 (유저가 북마크 상태를 변경할 수 있도록 기억)
    var restaurantList by remember {
        mutableStateOf(
            listOf(
                Restaurant("1", "홍대 화끈한 불닭발 본점", "요리주점", "230m", "서울 마포구 어울마당로 45", 4.8, 124),
                Restaurant("2", "동대문 엽기매운 화닭발", "종합분식", "450m", "서울 마포구 와우산로 21길", 4.3, 89),
                Restaurant("3", "불타는 청춘 닭발&오돌뼈", "한식", "810m", "서울 마포구 독막로 7길 12", 4.5, 53)
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI 추천 결과", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextMain) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "뒤로가기", tint = TextMain)
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: 공유 기능 */ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "공유", tint = TextMain)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF9F5F6) // 연한 그레이핑크 배경으로 고급스러운 톤앤매너 형성
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. AI 메인 메뉴 추천 카드 섹션
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "오늘 당신에게 정답이 될 한 끼 🎯",
                            fontSize = 14.sp,
                            color = PinkPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = recommendedMenu,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextMain
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // AI 비서 한 줄 코멘트 영역
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(PinkSecondary)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = aiComment,
                                fontSize = 14.sp,
                                color = TextMain.copy(alpha = 0.9f),
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }

            // 2. 섹션 타이틀: 주변 맛집 추천
            item {
                Column {
                    Text(
                        text = "지출 예산 내 추천 맛집 리스트 📍",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextMain
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "현재 내 위치 반경에 있는 식당 정보입니다.",
                        fontSize = 13.sp,
                        color = TextSub
                    )
                }
            }

            // 3. 맛집 리스트 아이템 렌더링
            items(restaurantList) { restaurant ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRestaurantClick(restaurant) },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 식당 기본 텍스트 영역
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = restaurant.name,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextMain,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = restaurant.category,
                                    fontSize = 11.sp,
                                    color = TextSub
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = null,
                                    tint = PinkPrimary,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "${restaurant.distance} · ${restaurant.address}",
                                    fontSize = 13.sp,
                                    color = TextSub,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "⭐️ ${restaurant.rating}", fontSize = 13.sp, color = RatingColor, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "리뷰 ${restaurant.reviewCount}", fontSize = 13.sp, color = TextSub)
                            }
                        }

                        // 하트 찜하기 토글 버튼 (클릭 시 상태 반전)
                        IconButton(
                            onClick = {
                                restaurantList = restaurantList.map {
                                    if (it.id == restaurant.id) it.copy(isBookmarked = !it.isBookmarked) else it
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (restaurant.isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "맛집 저장 토글",
                                tint = PinkPrimary,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }
                }
            }

            // 여백 확보용 아이템
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@DevicePreviews
@Composable
fun ResultScreenPreview() {
    WhatEatTheme {
        ResultScreen()
    }
}