package com.busanit.ch13_login.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch13_login.RetrofitClient
import com.busanit.ch13_login.databinding.ActivityRegisterBinding
import com.busanit.ch13_login.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 버튼 클릭 -> API 요청
        binding.buttonRegister.setOnClickListener {
            // 입력된 정보를 가져와 요청할 객체 생성
            val username = binding.EditTextUsername.text.toString()
            val password = binding.EditTextPassword.text.toString()
            val user = User(username, password)

            // 레트로핏으로 API 비동기 요청
            RetrofitClient.api.register(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                        Log.d("mylog", "onResponse: ${response.body()}")
                        // 회원 가입 성공시 메시지 띄우고, 로그인 화면으로 이동
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else {
                        // 실패
                        Toast.makeText(this@RegisterActivity, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        Log.d("mylog", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    // 네트워크 오류
                    Toast.makeText(this@RegisterActivity, "회원가입 네트워크 요청 실패", Toast.LENGTH_SHORT).show()
                    Log.d("mylog", "onResponse: ${t.message}")
                }

            })
        }

    }
}