package com.busanit.ch08_activity.ex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch08_activity.databinding.ActivityEx02SecondaryBinding

class Ex02SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEx02SecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val extra = intent.getStringExtra("extra_msg")

            textView.text = extra

            button.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("result_msg", "${extra}")
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

    }
}