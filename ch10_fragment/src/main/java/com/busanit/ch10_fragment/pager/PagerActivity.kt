package com.busanit.ch10_fragment.pager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.busanit.ch10_fragment.databinding.ActivityPagerBinding

class PagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager2.adapter = Adapter(this)

        // 스크롤 방향을 수직 방향으로 설정 (기본 값: 수평 방향)
        // binding.pager2.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
    
    // ViewPager2 어댑터
    class Adapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
        // 페이지 수를 반환
        override fun getItemCount(): Int = 3

        // position 번째의 프래그먼트 생성하여 반환
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> Fragment1()
                1 -> Fragment2()
                else -> Fragment3()
            }
        }

    }
}