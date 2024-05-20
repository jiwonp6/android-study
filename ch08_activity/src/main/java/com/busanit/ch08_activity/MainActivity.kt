package com.busanit.ch08_activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityMainBinding
import com.busanit.ch08_activity.intent.Intent1Activity
import com.busanit.ch08_activity.intent.Intent2Activity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding   // 지연 초기화 바인딩 객체 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            // 명시적 인텐트 사용
            button1.setOnClickListener {
                val intent = Intent(this@MainActivity, Intent1Activity::class.java)

                // Context 에서 액티비티를 시작
                startActivity(intent)
            }

            // 암시적 인텐트 사용
            button2.setOnClickListener {
                val uri = Uri.parse("http://naver.com")

                // 첫 번째 매개변수: Action, 두 번째 매개변수: URI
                val intent = Intent(Intent.ACTION_VIEW, uri)

                startActivity(intent)
            }

            // 암시적 인텐트 사용2 - 특정 전화번호로 전화
            button3.setOnClickListener {
                val phoneNumber = "tel: 01012345678"

                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))

                startActivity(intent)
            }

            // 암시적 인텐트 사용3 - 문자메세지 보내기
            button4.setOnClickListener {
                val phoneNumber = "tel: 01012345678"

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(phoneNumber))
                intent.putExtra("sms_body", "Hello! Message~!")

                startActivity(intent)
            }

            // 암시적 인텐트 사용4 - 공유 기능
            button5.setOnClickListener {
                // 인텐트 객체 생성하고 apply 스콥 함수 속성 설정
                val intent = Intent().apply {
                    // Action 설정: 데이터를 다른 앱으로 보내는 액션
                    action = Intent.ACTION_SEND

                    // 인텐트에 데이터를 추가
                    putExtra(Intent.EXTRA_TEXT, "공유 메시지 텍스트")   // 데이터
                    type = "text/plain"                                    // 데이터 타입 설정
                }
                // createChooser 메소드: 사용자에게 앱 선택 창을 표시하는 새로운 인텐트
                // 첫 번째 매개변수: 공유할 인텐트, 두 번째 매개변수: 제목
                val shareIntent = Intent.createChooser(intent, null)

                startActivity(shareIntent)
            }

            // 암시적 인텐트 사용6 - 데이터 전달
            button6.setOnClickListener {
                val intent = Intent(this@MainActivity, Intent1Activity::class.java)

                intent.putExtra("Extra1", "보내는 문자열")
                intent.putExtra("Extra2", 100)

                startActivity(intent)
            }

            // 암시적 인텐트 사용7 - 데이터 반환
            button7.setOnClickListener {
                val intent = Intent(this@MainActivity, Intent2Activity::class.java).apply {
                    putExtra("extra_msg", "Hello")
                }

                // 해당 액티비티를 시작하고 결과를 요청
                // 두 번째 매개변수: 요청코드
                startActivityForResult(intent, 10)
            }

            // Contract 객체 생성: 다른 Activity에 갔다 올 경우
            val contract = ActivityResultContracts.StartActivityForResult()

            // Launcher 객체 생성 (contract, call back 함수)
            val launcher = registerForActivityResult(contract) {
                // call back 함수: 돌아왔을 때 코드를 구현
                Toast.makeText(this@MainActivity, "다른 액티비티 갔다가 돌아옴", Toast.LENGTH_SHORT).show()

                // it: ActivityResult 객체
                // it.resultCode: 결과 코드
                // it.data: 결과 데이터
                if (it.resultCode == RESULT_OK) {
                    val result = it.data?.getStringExtra("result_msg")

                    textView.text = result
                }
            }

            button8.setOnClickListener {
                val intent = Intent(this@MainActivity, Intent2Activity::class.java)
                intent.putExtra("extra_msg", "Result API에서 보낸 메시지")
                
                launcher.launch(intent)
            }

        }

    }

    // 액티비티의 결과가 돌아왔을 때 수행되는 코드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // requestCode: 요청한 코드
        // resultCode: 결과 코드
        // data: 결과로 설정된 Intent 객체

        // 요청코드와 결과코드가 맞을 때
        if (requestCode == 10 && resultCode == RESULT_OK) {
            // 인텐트 객체에서 데이터를 가져옴
            val result = data?.getStringExtra("result_msg")
            binding.textView.text = result      // 뷰 객체에 텍스트 설정
        }
    }
}