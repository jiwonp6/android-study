package com.busanit.ch05_dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch05_dialog.databinding.ActivityToastBinding
import com.busanit.ch05_dialog.databinding.CustomSnackbarBinding
import com.google.android.material.snackbar.Snackbar

class ToastActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")      // 경고를 무시하는 애너테이션
    @RequiresApi(30)    // 하위 호환성 에너테이션의 경우 오류를 무시하기만 함
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToastBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_toast)
        setContentView(binding.root)
        binding.button1.setOnClickListener {
            // 토스트 메시지 띄우기
            // context, 메시지, 출력되는 시간(Toast.LENGTH_SHORT: 3s 정도 / Toast.LENGTH_LONG: 5s 정도)
            val toast = Toast.makeText(this, "일반 토스트 메시지입니다.", Toast.LENGTH_SHORT)
            toast.show()
        }

        binding.button2.setOnClickListener {
            // API 하위 호환성 고려하기
            // 1. 에너테이션 지정 2. if 문을 통해서 실행하기
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val toast = Toast.makeText(this, "조금 긴 토스트 메시지입니다.", Toast.LENGTH_LONG)
                // 토스트 객체가 나타나고 사라질 때 콜백 이벤트 추가
                toast.addCallback(object : Toast.Callback() {
                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("mylog", "토스트가 등장할 때 작동하는 콜백")
                    }

                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("mylog", "토스트가 사라질 때 작동하는 콜백")
                    }
                })

                toast.show()
            }
        }

        binding.button3.setOnClickListener {
            // 기본 스낵바
            Snackbar.make(it, "스낵바 메시지입니다.", Snackbar.LENGTH_SHORT).show()
        }

        binding.button4.setOnClickListener {
            // 액션 스낵바
            val snackBar = Snackbar.make(it, "액션을 취합니다.", Snackbar.LENGTH_LONG)
            snackBar.setTextColor(Color.YELLOW)
            snackBar.setBackgroundTint(Color.RED)
            snackBar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
            
            // 스낵바 액션 설정
            snackBar.setAction("OK") {
                binding.textView.text = "스낵바 OK를 눌렀습니다."
            }

            snackBar.show()

        }

        binding.button5.setOnClickListener {
            val snackBar = Snackbar.make(it, "", Snackbar.LENGTH_LONG)

            // 커스텀 스낵바 레이아웃 설정하기
            val snackBarBinding = CustomSnackbarBinding.inflate(layoutInflater)

            val snackbarLayout = snackBar.view as Snackbar.SnackbarLayout
            snackbarLayout.addView(snackBarBinding.root)

            snackBarBinding.actionBtn1.setOnClickListener {
                binding.textView.text = "액션1 클릭"
            }

            snackBarBinding.actionBtn2.setOnClickListener {
                binding.textView.text = "액션2 클릭"
            }

            snackBar.show()
        }
    }
}