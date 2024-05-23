package com.busanit.ch10_fragment.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.busanit.ch10_fragment.databinding.ActivityTabRecyclerBinding
import com.busanit.ch10_fragment.pager.Fragment2
import com.busanit.ch10_fragment.pager.Fragment3
import com.google.android.material.tabs.TabLayoutMediator

class TabRecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTabRecyclerBinding

    // 표시할 프래그먼트를 담을 리스트
    val fragmentList = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {super.onCreate(savedInstanceState)
        binding = ActivityTabRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 사용할 프래그먼트
        fragmentList.add(FragmentRecycler())
        fragmentList.add(Fragment2())
        fragmentList.add(Fragment3())

        binding.pager2.adapter = TabAdapter(this, fragmentList)

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