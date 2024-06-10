package com.busanit.ch14_etc.scrollView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch14_etc.databinding.ActivityScrollViewBinding

class ScrollViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityScrollViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}