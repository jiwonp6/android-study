package com.busanit.ch01_layout.practice01

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch01_layout.R

class Layout01Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_layout01)

        // 리소스의 id 값으로 뷰 객체를 얻음
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        
        button1.setOnClickListener{
            println("버튼 1번 클릭, 버튼 2번 숨김")
            button2.visibility = View.INVISIBLE
        }

        button3.setOnClickListener {
            println("버튼 3번 클릭, 버튼 2번 숨김해제")
            button2.visibility = View.VISIBLE
        }
        
    }
}