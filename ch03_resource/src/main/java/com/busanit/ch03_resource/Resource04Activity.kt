package com.busanit.ch03_resource

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Resource04Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resource04)

        val textView1 = findViewById<TextView>(R.id.TextView5)
        val textView2 = findViewById<TextView>(R.id.TextView5)
        val imageView = findViewById<ImageView>(R.id.ImageView2)

        // 플랫폼 리소스 사용 시 android.R 로 접근
        textView1.setText(android.R.string.paste)
        textView2.setText(android.R.string.copy)
        textView2.setTextColor(resources.getColor(android.R.color.holo_green_dark))

        imageView.setImageResource(android.R.drawable.ic_dialog_alert)

    }
}