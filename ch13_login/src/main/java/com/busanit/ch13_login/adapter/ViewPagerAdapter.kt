package com.busanit.ch13_login.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.busanit.ch13_login.fragment.ArticleFragment
import com.busanit.ch13_login.fragment.HomeFragment
import com.busanit.ch13_login.fragment.SettingFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    private val fragments = listOf(
        HomeFragment(),
        ArticleFragment(),
        SettingFragment()
    )
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}