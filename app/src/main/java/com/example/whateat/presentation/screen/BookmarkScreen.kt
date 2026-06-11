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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    onRestaurantClick: (Restaurant) -> Unit = {}
) {
    // 🎨 테마 컬러 매칭
    val PinkPrimary = Color(0xFFFF6B8B)
    val PinkSecondary = Color(0xFFFFF0F2)
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)
    val BorderColor = Color(0xFFF0E4E6)
    val RatingColor = Color(0xFFFFB800)

    // 1. 카테고리 필터 상태 및 리스트
    var selectedCategory by remember { mutableStateOf("전체") }
    val categories = listOf("전체", "한식", "일식", "중식/양식", "요리주점", "분식/디저트")

    // 2. 저장된 맛집 더미 데이터 (ResultScreen에서 하트 누른 가상의 데이터들)
    var bookmarkedRestaurants by remember {
        mutableStateOf(
            listOf(
                Restaurant("1", "홍대 화끈한 불닭발 본점", "요리주점", "230m", "서울 마포구 어울마당로 45", 4.8, 124, true),
                Restaurant("2", "동대문 엽기매운 화닭발", "분식/디저트", "450m", "서울 마포구 와우산로 21길", 4.3, 89, true),
                Restaurant("4", "미도인 홍대", "일식", "600m", "서울 마포구 잔다리로2길 19", 4.6, 245, true),
                Restaurant("5", "윤씨밀방", "중식/양식", "720m", "서울 마포구 와우산로15길 15", 4.4, 512, true)
            )
        )
    }

    // 선택된 카테고리에 맞게 리스트 필터링
    val filteredRestaurants = if (selectedCategory == "전체") {
        bookmarkedRestaurants
    } else {
        bookmarkedRestaurants.filter { it.category == selectedCategory }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // 상단 타이틀 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 12.dp)
        ) {
            Text(
                text = "나의 맛집 저장소 📂",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "AI가 추천해 준 메뉴 중 마음에 들었던 식당들이에요.",
                fontSize = 13.sp,
                color = TextSub
            )
        }

        // ------------------------------------------
        // 1. 수평 스크롤 카테고리 탭 (LazyRow)
        // ------------------------------------------
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = selectedCategory == category
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) PinkPrimary else PinkSecondary.copy(alpha = 0.4f))
                        .clickable { selectedCategory = category }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.White else TextMain
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ------------------------------------------
        // 2. 저장된 맛집 리스트 (LazyColumn)
        // ------------------------------------------
        if (filteredRestaurants.isEmpty()) {
            // 저장된 맛집이 없을 때 보여줄 예외 화면
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "해당 카테고리에 저장된 맛집이 없어요. 🤔\n추천 화면에서 마음에 드는 식당을 찜해보세요!",
                    fontSize = 14.sp,
                    color = TextSub,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredRestaurants, key = { it.id }) { restaurant ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onRestaurantClick(restaurant) },
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // 상단부: 이름, 카테고리, 하트 해제 버튼
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
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
                                    Spacer(modifier = Modifier.height(4.dp))
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
                                }

                                // 찜 취소 버튼 (클릭 시 리스트에서 지워지거나 상태 토글)
                                IconButton(
                                    onClick = {
                                        // 실시간으로 찜 목록에서 제외하는 액션
                                        bookmarkedRestaurants = bookmarkedRestaurants.filter { it.id != restaurant.id }
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "찜 해제",
                                        tint = PinkPrimary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            // 중단부: 평점 및 리뷰 개수
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "⭐️ ${restaurant.rating}", fontSize = 13.sp, color = RatingColor, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "리뷰 ${restaurant.reviewCount}", fontSize = 13.sp, color = TextSub)
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // 하단부: 실제 액션 버튼들 (길찾기 / 전화)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // 1. 전화 걸기 버튼
                                OutlinedButton(
                                    onClick = { /* TODO: 전화 인텐트 연동 */ },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextMain),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderColor),
                                    contentPadding = PaddingValues(vertical = 10.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("전화하기", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                                }

                                // 2. 길찾기 (외부 맵 연동) 버튼
                                Button(
                                    onClick = { /* TODO: 카카오/네이버맵 외부 연동 */ },
                                    modifier = Modifier.weight(1.3f),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                                    contentPadding = PaddingValues(vertical = 10.dp)
                                ) {
                                    Text("바로 길찾기 🔥", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                }
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
fun BookmarkScreenPreview() {
    WhatEatTheme {
        BookmarkScreen()
    }
}