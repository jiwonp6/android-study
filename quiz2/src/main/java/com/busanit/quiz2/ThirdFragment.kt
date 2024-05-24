package com.busanit.quiz2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.busanit.quiz2.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        binding.run {
            button3.setOnClickListener {
                val intent = Intent(root.context, ResultActivity::class.java)
                intent.putExtra("extra_msg", textView3.text)
                startActivityForResult(intent, 10)
            }
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == AppCompatActivity.RESULT_OK) {
            val result = data?.getStringExtra("result_msg")
            binding.textView3.text = result
        }
    }
}