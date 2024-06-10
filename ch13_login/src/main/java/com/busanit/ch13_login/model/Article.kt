package com.busanit.ch13_login.model

// 게시글 데이터 클래스
data class Article(
    val id: Long,
    val title: String,
    val content: String,
    val author: String
)
