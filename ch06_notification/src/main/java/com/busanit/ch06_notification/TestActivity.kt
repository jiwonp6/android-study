package com.busanit.ch06_notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch06_notification.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트에서 데이터를 가져와 텍스트 뷰에 출력
        binding.textView.text = intent.getStringExtra("msg")
        binding.textView.append(intent.getStringExtra("data"))
    }
}