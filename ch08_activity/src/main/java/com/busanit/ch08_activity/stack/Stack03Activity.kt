package com.busanit.ch08_activity.stack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityStack03Binding

class Stack03Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStack03Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            oneTextView.setOnClickListener {
                // CLEAR_TOP: 지정된 액티비티가 이미 스택에 존재하면,
                // 그 위의 모든 액티비티를 제거하고 지정한 액티비티를 최상단으로 보냄
                // 홈 화면으로 바로 돌아갈 때 사용
                val intent01 = Intent(this@Stack03Activity, Stack01Activity::class.java)
                intent01.apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                startActivity(intent01)
            }

            twoTextView.setOnClickListener {
                val intent02 = Intent(this@Stack03Activity, Stack02Activity::class.java)
                startActivity(intent02)
            }

            threeTextView.setOnClickListener {
                // NEW_TASK: 새로운 태스크에서 액티비티를 시작,
                // 브라우저나 외부 애플리케이션에서 액티비티를 시작할 때
                val intent03 = Intent(this@Stack03Activity, Stack03Activity::class.java)
                intent03.apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent03)
            }
        }
    }
}