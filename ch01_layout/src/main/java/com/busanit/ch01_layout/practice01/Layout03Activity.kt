package com.busanit.ch01_layout.practice01

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch01_layout.R

class Layout03Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_layout03)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        button1.setOnClickListener {
            button4.visibility = View.VISIBLE
        }
        button2.setOnClickListener {
            button4.visibility = View.INVISIBLE
        }
        button3.setOnClickListener {
            button4.visibility = View.GONE
        }
    }
}