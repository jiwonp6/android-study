package com.busanit.ch07_appbar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch07_appbar.databinding.ActivityUpButtonBinding

class UpButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            // 두 번째 액티비티로 넘어가는 메시지 생성
            val intent = Intent(this, UpSecondActivity::class.java)
            
            // 액티비티 시작
            startActivity(intent)

        }
    }
}