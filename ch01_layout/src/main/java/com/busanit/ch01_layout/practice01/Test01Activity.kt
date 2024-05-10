package com.busanit.ch01_layout.practice01

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.busanit.ch01_layout.R

class Test01Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test01)

        // 이름 문자열 출력하는 TextView 생성
        val name = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = "레이크 루이즈 호수"
        }

        // 이미지 출력 ImageView 생성
        val image = ImageView(this).also {
            it.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lake_1))
        }

        // 텍스트 뷰 생성
        val address = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = "캐나다"
        }

        // 레이아웃 객체 생성
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL    // 방향
            gravity = Gravity.CENTER               // 정렬
            
            // 레이아웃이 View 객체 추가
            addView(name, WRAP_CONTENT, WRAP_CONTENT)       // 객체, 너비, 높이
            addView(image, WRAP_CONTENT, WRAP_CONTENT)
            addView(address, WRAP_CONTENT, WRAP_CONTENT)
            // WRAP_CONTENT: 내용물의 크기만큼 객체가 크기가 감쌈
        }

        // LinearLayout 객체를 화면에 출력
        setContentView(layout)
    }
}