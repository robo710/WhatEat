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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whateat.presentation.component.DevicePreviews
import com.example.whateat.ui.theme.WhatEatTheme

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    onEditPreferenceClick: () -> Unit = {}, // 취향 설정 수정 페이지 이동 액션
    onEditProfileClick: () -> Unit = {}    // 프로필 수정 페이지 이동 액션
) {
    // 🎨 테마 컬러 매칭 (기존 화면들과 톤앤매너 통일)
    val PinkPrimary = Color(0xFFFF6B8B)
    val PinkSecondary = Color(0xFFFFF0F2)
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)
    val BorderColor = Color(0xFFF0E4E6)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFF9F5F6)) // 일관성 있는 연그레이핑크 배경
    ) {
        // 상단 타이틀 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 12.dp)
        ) {
            Text(
                text = "내 정보 👤",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextMain
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "개인 설정 및 맞춤형 AI 추천 기준을 관리합니다.",
                fontSize = 13.sp,
                color = TextSub
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // ------------------------------------------
            // 1. 유저 간단 프로필 카드 (비회원 기반 로컬 프로필)
            // ------------------------------------------
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 프로필 기본 이모지/이미지 영역
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(PinkSecondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "프로필",
                                tint = PinkPrimary,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // 유저 정보 및 메타 텍스트
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "오늘뭐먹지 유저",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextMain
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "AI와 함께 가성비 한 끼 탐험 중 🚀",
                                fontSize = 12.sp,
                                color = TextSub
                            )
                        }

                        // 프로필 수정 화살표
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "프로필 수정",
                            tint = TextSub,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { onEditProfileClick() }
                        )
                    }
                }
            }

            // ------------------------------------------
            // 2. 설정 메뉴 그룹 1: 개인 맞춤형 기준 (AI 추천 필터)
            // ------------------------------------------
            item {
                Text(
                    text = "나의 취향 맞춤 대시보드",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSub,
                    modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        MyPageMenuItem(
                            icon = Icons.Default.Star,
                            title = "못 먹는 음식 / 알레르기 관리",
                            subtitle = "AI 추천에서 완전히 제외할 필터 설정",
                            iconContainerColor = Color(0xFFFFECEF),
                            iconTint = PinkPrimary,
                            onClick = onEditPreferenceClick
                        )
                        Divider(color = BorderColor, modifier = Modifier.padding(horizontal = 16.dp))
                        MyPageMenuItem(
                            icon = Icons.Default.Notifications,
                            title = "밥 때 알림 설정 (AI 추천 픽)",
                            subtitle = "점심/저녁 시간 맞춰 메뉴 제안 푸시 수신",
                            iconContainerColor = Color(0xFFE8F5E9),
                            iconTint = Color(0xFF4CAF50),
                            onClick = { /* TODO: 알림 설정 팝업 또는 시스템 설정 연동 */ }
                        )
                    }
                }
            }

            // ------------------------------------------
            // 3. 설정 메뉴 그룹 2: 로컬 데이터 및 앱 관리 (수정본)
            // ------------------------------------------
            item {
                Text(
                    text = "앱 관리",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSub,
                    modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        MyPageMenuItem(
                            icon = Icons.Default.Delete, // 쓰레기통 아이콘으로 변경하여 직관성 확보
                            title = "저장된 데이터 초기화",
                            subtitle = "기기에 저장된 맛집 보관함 및 먹방 기록 전체 삭제",
                            iconContainerColor = Color(0xFFECEFF1),
                            iconTint = Color(0xFF607D8B),
                            onClick = { /* TODO: Room DB 또는 DataStore 데이터 clear 로직 연동 */ }
                        )
                        Divider(color = BorderColor, modifier = Modifier.padding(horizontal = 16.dp))
                        MyPageMenuItem(
                            icon = Icons.Default.Info, // 정보 아이콘으로 변경
                            title = "서비스 이용 정보",
                            subtitle = "앱 버전 v1.0.0 · 오픈소스 라이선스",
                            iconContainerColor = Color(0xFFFFF3E0),
                            iconTint = Color(0xFFFF9800),
                            onClick = { /* TODO: 다이얼로그 팝업 노출 */ }
                        )
                    }
                }
            }
        }
    }
}

// ------------------------------------------
// 🛠️ 마이페이지 전용 재사용 공통 메뉴 아이템 컴포저블
// ------------------------------------------
@Composable
fun MyPageMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    iconContainerColor: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    val TextMain = Color(0xFF332A2B)
    val TextSub = Color(0xFF8A7E80)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 아이콘 백그라운드 스퀘어 라운드 박스 처리
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconContainerColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // 메뉴 제목 및 서브 타이틀 설명란
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextMain
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = TextSub
                )
            }
        }

        // 우측 이동 방향 지시자 화살표
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFFCCCCCC),
            modifier = Modifier.size(20.dp)
        )
    }
}

@DevicePreviews
@Composable
fun MyPageScreenPreview() {
    WhatEatTheme {
        MyPageScreen()
    }
}