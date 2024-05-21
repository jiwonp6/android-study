package com.busanit.ch08_activity.stack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityStack02Binding

class Stack02Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStack02Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            oneTextView.setOnClickListener {
                val intent01 = Intent(this@Stack02Activity, Stack01Activity::class.java)
                startActivity(intent01)
            }

            twoTextView.setOnClickListener {
                // SINGLE_TOP: 동일한 액티비티가 스택의 맨 위에 존재하면 기존 인스턴스 재사용, 불필요한 인스턴스 생성 방지
                val intent02 = Intent(this@Stack02Activity, Stack02Activity::class.java)
                intent02.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent02)
            }

            threeTextView.setOnClickListener {
                val intent03 = Intent(this@Stack02Activity, Stack03Activity::class.java)
                startActivity(intent03)
            }
        }
    }
}