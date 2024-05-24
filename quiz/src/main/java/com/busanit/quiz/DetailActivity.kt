package com.busanit.quiz

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.busanit.quiz.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.run {
            val editText = intent.getStringExtra("editText")
            val check = intent.getBooleanExtra("checkBox", false)
            Log.d("mylog", "onCreate: $check")
            textView.text = editText
            checkBox.isChecked = check
        }

        binding.button.setOnClickListener {
            finish()
        }
    }
}