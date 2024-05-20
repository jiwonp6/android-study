package com.busanit.ch08_activity.ex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityEx01SecondaryBinding

class Ex01SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEx01SecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val edit = intent.getStringExtra("edit_msg")
            textView.text = edit

            button.setOnClickListener {
                finish()
            }
        }
    }
}