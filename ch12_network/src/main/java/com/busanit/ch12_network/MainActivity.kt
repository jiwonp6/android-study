package com.busanit.ch12_network

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch12_network.databinding.ActivityMainBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "mylog"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 포스트 목록 가져오기
        // getPosts()

        // Id로 포스트 가져오기
        getPostById()
    }

    private fun getPostById() {
        // 레트로핏 클라이언트 인스턴스 선언
        val api = RetrofitClient.instance

        // API 를 호출
        api.getPostById(1).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    Log.d(TAG, "onResponse: ${post}")

                    // 응답이 성공적인 경우 View 업데이트
                    binding.textView.text = post?.body ?: "empty"

                }

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d(TAG, "onFailure: 네트워크 요청 실패하였습니다. ${t.message}")
            }
        })
    }

    private fun getPosts() {
        // 레트로핏 클라이언트 인스턴스 선언
        val api = RetrofitClient.instance

        // API 를 호출 (비동기 방식)
        api.getPosts().enqueue(object : Callback<List<Post>> {
            // 네트워크 응답을 성공적으로 받았을 때
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.d(TAG, "onResponse: 네트워크 요청 성공하였습니다.")

                // 성공 시 상태 코드 200 번 대의 응답을 받음
                Log.d(TAG, "onResponse: 상태 코드 (${response.code()})")

                Log.d(TAG, "onResponse: 응답 헤더 (${response.headers()})")

                // 상태 코드가 200 번 대인 경우: true
                if (response.isSuccessful) {
                    // 응답에서 데이터(본문, body) 추출
                    val posts = response.body()
                    Log.d(TAG, "onResponse: 응답 본문 (${posts})")
                }
            }

            // 네트워크 요청 실패했을 때
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d(TAG, "onFailure: 네트워크 요청 실패하였습니다.")
            }
        })
    }
}