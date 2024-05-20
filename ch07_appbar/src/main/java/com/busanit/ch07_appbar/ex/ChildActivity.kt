package com.busanit.ch07_appbar.ex

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch07_appbar.databinding.ActivityChildBinding

class ChildActivity : AppCompatActivity() {
    lateinit var binding: ActivityChildBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 업버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    // 아이템 선택
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}