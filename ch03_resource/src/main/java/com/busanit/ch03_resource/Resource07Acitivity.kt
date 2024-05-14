package com.busanit.ch03_resource

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Resource07Acitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource07_acitivity)

        // 기기의 가로 세로 크기 가져오기
        // API 버전 30이상인 경우
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics

            val width = windowMetrics.bounds.width()
            val height = windowMetrics.bounds.height()

            Log.d("myLog", "기기 넓이: ${width}, 기기높이: ${height}")
        } else {
            // API 버전 30이하인 경우
            val display = windowManager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display.getRealMetrics(displayMetrics)

            val width = displayMetrics.widthPixels
            val height = displayMetrics.heightPixels

            Log.d("myLog", "기기 넓이: ${width}, 기기높이: ${height}")

        }
    }
}