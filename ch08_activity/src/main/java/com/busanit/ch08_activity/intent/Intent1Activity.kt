package com.busanit.ch08_activity.intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityIntent1Binding

class Intent1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntent1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra1 = intent.getStringExtra("Extra1")
        val extra2 = intent.getIntExtra("Extra2", 0)

        binding.textView2.text = extra1

    }
}