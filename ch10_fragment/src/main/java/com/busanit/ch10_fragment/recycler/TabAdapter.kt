package com.busanit.ch10_fragment.recycler

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fragmentActivity: FragmentActivity, val fragmentList: MutableList<Fragment>): FragmentStateAdapter(fragmentActivity, ) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = fragmentList[position]

}