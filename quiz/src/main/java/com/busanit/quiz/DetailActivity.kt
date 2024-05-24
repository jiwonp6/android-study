package com.busanit.quiz

import android.content.Intent
import android.os.Bundle
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
            textView.text = editText
            checkBox.isChecked = check
        }

        binding.button.setOnClickListener {
            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}