package com.busanit.ch07_appbar.ex

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch07_appbar.R
import com.busanit.ch07_appbar.databinding.ActivityMainBinding

/*
    [문제]
    `MainActivity`에 기본 액션 바를 설정하고, `ChildActivity`에 업 버튼을 추가하세요.
    업 버튼을 클릭하면 `MainActivity`로 돌아가도록 구현하세요.
    메뉴 아이템을 만들어보세요.

    [단계]
    1. AndroidManifest.xml 파일에서 `ChildActivity`의 상위 액티비티를 `MainActivity`로 설정하세요.
    2. `ChildActivity`에서 업 버튼을 활성화하고 클릭 이벤트를 처리하세요.
    3. `res/menu` 디렉토리에 `menu.xml` 파일을 생성하고 메뉴 항목을 정의하세요.
    4. MainActivity에 메뉴 항목 "Search"와 "Settings"를 추가하고, 각 메뉴 항목 클릭 시 이벤트가 발동하게 하세요.
    5. `MainActivity`에서 `onCreateOptionsMenu`와 `onOptionsItemSelected` 메서드를 오버라이드하여 메뉴를 설정하고 클릭 이벤트를 처리하세요.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    // 메뉴 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 클릭 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            }
            R.id.setting -> {
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}