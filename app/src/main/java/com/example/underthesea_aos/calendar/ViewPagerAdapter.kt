package com.example.underthesea_aos.calendar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    private var fragments : ArrayList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    fun addItems(fragment : Fragment){
        fragments.add(fragment)
    }
}
