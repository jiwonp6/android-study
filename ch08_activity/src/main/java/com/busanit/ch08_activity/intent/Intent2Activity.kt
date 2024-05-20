package com.busanit.ch08_activity.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityIntent2Binding

// 인텐트 결과 반환
class Intent2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntent2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            // 호출한 인텐트로부터 데이터를 받음
            val extra = intent.getStringExtra("extra_msg")

            textView.text = extra

            // 버튼을 클릭하면 반환할 인텐트를 생성하고 액티비티 종료
            button1.setOnClickListener {
                val resultIntent = Intent()     // 인텐트 객체 생성
                resultIntent.putExtra("result_msg", "${extra}, Result from Other Activity")

                setResult(RESULT_OK, resultIntent)
                finish()    // 액티비티 종료
            }
        }

    }
}