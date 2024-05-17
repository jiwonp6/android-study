package com.busanit.ch07_appbar

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch07_appbar.databinding.ActivityUpSecondBinding

class UpSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 홈 버튼 활성화
        supportActionBar?.setHomeButtonEnabled(true)
        // 홈 버튼 노출 활성화
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