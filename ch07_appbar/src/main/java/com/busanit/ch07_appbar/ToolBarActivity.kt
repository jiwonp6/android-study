package com.busanit.ch07_appbar

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch07_appbar.databinding.ActivityToolBarBinding

class ToolBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityToolBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolBar.run {
            title = "Tool Bar"

            setTitleTextColor(Color.WHITE)

            inflateMenu(R.menu.menu01)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item1 -> {
                        Toast.makeText(this@ToolBarActivity, "항목 1을 눌렀습니다.", Toast.LENGTH_SHORT)
                            .show()
                        false
                    }

                    R.id.item2 -> {
                        Toast.makeText(this@ToolBarActivity, "항목 2를 눌렀습니다.", Toast.LENGTH_SHORT)
                            .show()
                        false
                    }

                    else -> false
                }
            }
        }

    }
}