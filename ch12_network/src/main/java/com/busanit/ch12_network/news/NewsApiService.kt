package com.busanit.ch12_network.news

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit 인터페이스 정의
interface NewsApiService {
    @GET("/v2/top-headlines")
    fun getTopHeadline( @Query("apiKey") apiKey: String, @Query("country") country: String): Call<NewsResponse>

}