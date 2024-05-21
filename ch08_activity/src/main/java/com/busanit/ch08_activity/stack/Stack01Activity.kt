package com.busanit.ch08_activity.stack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityStack01Binding

class Stack01Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStack01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            oneTextView.setOnClickListener {
                val intent01 = Intent(this@Stack01Activity, Stack01Activity::class.java)
                startActivity(intent01)
            }

            twoTextView.setOnClickListener {
                val intent02 = Intent(this@Stack01Activity, Stack02Activity::class.java)
                startActivity(intent02)
            }

            threeTextView.setOnClickListener {
                val intent03 = Intent(this@Stack01Activity, Stack03Activity::class.java)
                startActivity(intent03)
            }
        }
    }
}