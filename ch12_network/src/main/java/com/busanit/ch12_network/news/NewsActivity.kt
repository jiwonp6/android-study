package com.busanit.ch12_network.news

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_network.databinding.ActivityNewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "errorLog"

class NewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val api = NewsRetrofitClient.instance
        api.getTopHeadline("c4fabdff1e30410bb2845f8db091bcf0", "us").enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    // 네트워킹에 성공할 경우
                    val articles = response.body()?.articles ?: emptyList()

                    binding.recyclerView.adapter = ArticleAdapter(articles)
                } else {
                    // 오류처리
                    handleServerError(response)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                handleNetWorkError(t)
            }
        })

    }

    // 레트로핏 오류 처리
    private fun handleServerError(response: Response<*>) {
        when (response.code()) {
            400 -> Log.d(TAG, "400 Bad Request ${response.message()}")
            401 -> Log.d(TAG, "401 Unauthorized ${response.message()}")
            403 -> Log.d(TAG, "403 Forbidden ${response.message()}")
            404 -> Log.d(TAG, "404 Not Found ${response.message()}")
            500 -> Log.d(TAG, "500 Server Error ${response.message()}")
            else -> Log.d(TAG, "Response Error ${response.message()}")
        }
    }

    private fun handleNetWorkError(t: Throwable) {
        Log.d(TAG, "Network Error ${t.message}")
        Toast.makeText(this, "네트워크 요청 실패", Toast.LENGTH_SHORT).show()
    }
}