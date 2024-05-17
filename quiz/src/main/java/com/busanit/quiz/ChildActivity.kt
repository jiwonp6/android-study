package com.busanit.quiz

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.busanit.quiz.databinding.ActivityChildBinding

class ChildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityChildBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    // 아이템 선택
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // 선택한 아이템 = 홈 버튼의 플랫폼 아이디
            android.R.id.home -> {
                // UP 버튼이 눌렸을 때 동작을 정의
                finish()    // 현재 Activity 종료
            }
        }
        return super.onOptionsItemSelected(item)
    }

}