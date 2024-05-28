package com.busanit.ch11_persistence.room

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.busanit.ch11_persistence.databinding.ActivityRoomBinding
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomBinding

    lateinit var db: AppDatabase    // Room 데이터베이스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Room DB 인스턴스 생성 할당
        // Room.databaseBuilder(context, RoomDatabase 클래스, 데이터베이스 이름)
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "myroomdb.db").build()

        binding.run {
            // 1. 데이터 삽입
            buttonSave.setOnClickListener {
                // 데이터 가져오기
                val name = editTextName.text.toString()
                val age = editTextAge.text.toString().toIntOrNull()

                if (age == null) {
                    Toast.makeText(this@RoomActivity, "잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    // User 객체 생성
                    val user = User(name = name, age = age)

                    // DB 작업 (비동기 작업을 위한 코틀린 코루틴 스코프)
                    lifecycleScope.launch {
                        db.userDao().insert(user)   // DB에 삽입
                    }
                }
            }

            // 2. 데이터 조회
            buttonLoad.setOnClickListener {
                lifecycleScope.launch {
                    // 쿼리 실행 > List에 담김
                    val userList = db.userDao().getAll()

                    // 데이터를 표시 (리스트를 구분자(줄바꿈 문자)로 조인)
                    textView.text = userList.joinToString("\n")
                }
            }
        }
    }
}