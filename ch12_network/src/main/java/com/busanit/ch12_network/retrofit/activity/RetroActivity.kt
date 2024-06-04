package com.busanit.ch12_network.retrofit.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.ch12_network.databinding.ActivityRetroBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.adapter.PostAdapter
import com.busanit.ch12_network.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val userId = 1
class RetroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRetroBinding
    lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRetroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val api = RetrofitClient.instance
        api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    // 네트워킹에 성공할 경우 
                    val posts = response.body() ?: emptyList()
                    
                    // 리사이클러뷰 어댑터 매개변수를 통해 데이터 전달 + 어댑터 연결
                    adapter = PostAdapter(posts)
                    binding.recyclerView.adapter = PostAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // 실패 처리
                TODO("Not yet implemented")
            }
        })

        // Result API
        val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                adapter.notifyDataSetChanged()                   // 리사이클러뷰 데이터 셋 갱신
                binding.recyclerView.scrollToPosition(0) // 최상단으로 스크롤
            }

        // 버튼 클릭 시 글 작성 액티비티로 이동
        binding.buttonCreate.setOnClickListener {
            val intent = Intent(this, NewPostActivity::class.java)
            intent.putExtra("userId", userId)

            // 액티비티 결과 반환 X
            // startActivity(intent)

            // 액티비티 결과 반환
            activityResultLauncher.launch(intent)
            adapter.notifyDataSetChanged()
        }
    }
}