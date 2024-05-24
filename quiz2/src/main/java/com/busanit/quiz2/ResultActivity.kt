package com.busanit.quiz2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.quiz2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val extra = intent.getStringExtra("extra_msg")

            textView.text = extra

            button.setOnClickListener {
                val resultIntent = Intent(this@ResultActivity, ThirdFragment::class.java)
                val editText = binding.editText.text.toString()
                resultIntent.putExtra("result_msg", editText)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}