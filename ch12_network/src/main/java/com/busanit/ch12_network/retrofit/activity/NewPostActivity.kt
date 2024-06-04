package com.busanit.ch12_network.retrofit.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch12_network.databinding.ActivityNewPostBinding
import com.busanit.ch12_network.retrofit.RetrofitClient
import com.busanit.ch12_network.retrofit.model.NewPost
import com.busanit.ch12_network.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NewPostActivity"
class NewPostActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = RetrofitClient.instance

        binding.run {
            buttonSubmit.setOnClickListener {
                // 사용자로부터 데이터를 입력받아 데이터 객체 생성
                val title = editTextTitle.text.toString()
                val body = editTextBody.text.toString()

                val newPost = NewPost(userId = intent.getIntExtra("userId", 0), title = title, body = body)

                api.createPost(newPost = newPost).enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@NewPostActivity, "글 작성 완료", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "onResponse: ${response.body()}")
                            finish()    // 새 글 작성 성공 시, Activity 종료, 이전으로 돌아감
                        } else {
                            Toast.makeText(this@NewPostActivity, "글 작성 실패(응답 실패)", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "onResponse: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        // 실패 처리, 네트워크 요청 자체가 안돼서 예외(t)를 던짐
                        Toast.makeText(this@NewPostActivity, "네트워크 요청 응답 실패", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "onFailure: ${t.message}")
                    }

                })
            }
        }

    }
}