package com.busanit.quiz2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.busanit.quiz2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val fragmentList = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용할 프래그먼트
        fragmentList.add(FirstFragment())
        fragmentList.add(SecondFragment())
        fragmentList.add(ThirdFragment())

        binding.pager2.adapter = MyFragmentStateAdapter(this, fragmentList)

        // 탭 이름 설정
        val tabName = arrayOf("첫 번째 탭", "두 번째 탭", "세 번째 탭")

        binding.run {
            // TabLayout 과 ViewPager 를 연동하는 역할
            TabLayoutMediator(tabLayout, pager2) {
                    tab, position -> tab.text = tabName[position]   // 탭의 이름 설정
            }.attach()
        }

    }
}