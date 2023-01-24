package com.example.underthesea_aos.calendar

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.underthesea_aos.R

class ViewPagerFragment : Fragment() {
    lateinit var calendarViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.view_pager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        val firstFragmentStateAdapter
                = FirstFragmentStateAdapter(requireActivity())
        calendarViewPager.adapter = firstFragmentStateAdapter
        calendarViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        firstFragmentStateAdapter.apply {
            firstFragmentStateAdapter.notifyDataSetChanged()  //setCurrentItem(this.firstFragmentPosition, false)
        }
    }

}