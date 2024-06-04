package com.busanit.ch12_network.retrofit.model

// 데이터 추가를 요청하기 위한 클래스: POST 요청
data class NewPost(
    val userId: Int,
    val title: String,
    val body: String
)
