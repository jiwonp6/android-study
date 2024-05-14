package com.busanit.ch03_resource

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class Resource03Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resource03)

        // 텍스트 뷰에 폰트 지정
        val textView = findViewById<TextView>(R.id.textView3)
        textView.typeface = ResourcesCompat.getFont(this, R.font.danfo)
    }
}