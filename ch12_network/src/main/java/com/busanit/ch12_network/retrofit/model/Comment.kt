package com.busanit.ch12_network.retrofit.model

// REST API 서버의 응답에 맞는 데이터 클래스 생성
data class Comment (
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)