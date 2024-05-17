package com.busanit.ch07_appbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActionBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_bar)
    }

    // 옵션 메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 리소스의 메뉴 레이아웃을 인플레이트
        menuInflater.inflate(R.menu.menu01, menu)

        // 메뉴 구성 함수에서 메뉴 항목을 추가
        menu?.add(0, 4, 0, "항목5")
        menu?.add(0, 5, 0, "항목6")

        return super.onCreateOptionsMenu(menu)
    }

    // 메뉴 항목이 선택되었을 때 이벤트를 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this, "항목 1번 선택", Toast.LENGTH_SHORT).show()
            }
            R.id.item2 -> {
                Toast.makeText(this, "항목 2번 선택", Toast.LENGTH_SHORT).show()
            }
            R.id.item3 -> {
                Toast.makeText(this, "항목 3번 선택", Toast.LENGTH_SHORT).show()
            }
            R.id.item4 -> {
                Toast.makeText(this, "항목 4번 선택", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}