package com.busanit.ch14_etc.fab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch14_etc.databinding.ActivityFabBinding

class FabActivity : AppCompatActivity() {
    lateinit var binding: ActivityFabBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            // 플로팅 액션 버튼 이벤트

        }
    }
}