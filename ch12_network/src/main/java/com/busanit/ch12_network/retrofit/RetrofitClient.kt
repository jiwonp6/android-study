package com.busanit.ch12_network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 레트로핏 싱글톤 인스턴스 생성
object RetrofitClient {
    // 기본 URL
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: ApiService by lazy { // lazy: 지연 초기화
        // Retrofit 객체 생성
        val retrofit = Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create()) // JSON 변환을 위한 컨버터
                                .build()

        // API 인터페이스를 레트로핏을 사용하여 구현
        retrofit.create(ApiService::class.java)
    }
}