package com.busanit.ch03_resource

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

val TAG = "mylog"

class Resource02Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resource02)
        
        // 코드에서 문자열 사용하기
        val welcomeMsg = getString(R.string.welcome_message)
        val btnText = getString(R.string.btn_text)
        Log.d(TAG, welcomeMsg)
        
        // 텍스트 뷰에 리소스 문자열 적용
        val textView = findViewById<TextView>(R.id.textView1)
        textView.text = welcomeMsg
        
        // 텍스트 뷰에 문자열 크기 적용
        textView.textSize = resources.getDimension(R.dimen.text_size)
        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.primary_color, null))
    }
}