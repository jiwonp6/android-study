package com.busanit.ch12_network.retrofit.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_network.databinding.ActivityCommentBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.adapter.CommentAdapter
import com.busanit.ch12_network.retrofit.model.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // binding.recyclerView.adapter = CommentAdapter()

        // 호출한 intent 에서 값 가져옴 (없으면 default 로 -1)
        val postId = intent.getIntExtra("postId", -1)

        // 데이터 네트워크 호출
        val api = RetrofitClient.instance
        api.getCommentsByPostId(postId).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                // 네트워킹 성공
                if (response.isSuccessful) {
                    val comments = response.body() ?: emptyList()

                    binding.recyclerView.adapter = CommentAdapter(comments)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                // 실패
                TODO("Not yet implemented")
            }

        })

    }
}