package com.busanit.ch08_activity.ex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityEx01Binding

/*
    연습문제 : 인텐트를 사용하여 데이터 전달하기

    요구사항:

    1. `MainActivity`와 `SecondActivity`를 생성합니다.
    2. `MainActivity`에 `EditText`와 `Button`을 추가합니다.
    3. `Button`을 클릭하면 `EditText`에 입력된 텍스트를 `SecondActivity`로 전달하고, `SecondActivity`에서 전달받은 텍스트를 `TextView`에 표시합니다.
    4. `SecondActivity`에서 버튼을 추가하여, 버튼을 클릭하면 `MainActivity`로 돌아가도록 합니다.

    힌트:

    - 인텐트로 데이터 전달: `intent.putExtra("key", value)`
    - 데이터 수신: `intent.getStringExtra("key")`
 */
class Ex01Activity : AppCompatActivity() {
    lateinit var binding: ActivityEx01Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEx01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this@Ex01Activity, Ex01SecondaryActivity::class.java)
            intent.putExtra("edit_msg", "Hello~!")

            startActivity(intent)
        }

    }

}