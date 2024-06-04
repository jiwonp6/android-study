package com.busanit.ch12_network.retrofit

import com.busanit.ch12_network.retrofit.model.Comment
import com.busanit.ch12_network.retrofit.model.NewPost
import com.busanit.ch12_network.retrofit.model.Post

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// Retrofit 인터페이스 정의
interface ApiService {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    // 경로변수(Path)를 통해서 해당 id의 게시물 가져옴
    @GET("/posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    // 쿼리 파라미터를 통해서 동적으로 특정 게시물의 댓글 가져옴
    @GET("/comments")
    fun getCommentsByPostId(@Query("postId") postId: Int): Call<List<Comment>>

    // 요청 본문으로 받은 Post 객체 생성하고 반환
    @POST("/posts")
    fun createPost(@Body newPost: NewPost): Call<Post>

}