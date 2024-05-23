package com.busanit.ch10_fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.ch10_fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FragmentManager 에서 Fragment 를 추가
        supportFragmentManager.beginTransaction()               // 트랜잭션 시작
            .replace(R.id.fragment_container, SampleFragment()) // 기존의 Fragment 교체 (바꾸기 전 컨테이너, 바꿀 프래그먼트)
            .commit()                                           // 트랜잭션 커밋

    }
}