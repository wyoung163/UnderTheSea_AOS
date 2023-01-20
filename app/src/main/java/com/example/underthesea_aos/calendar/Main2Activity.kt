package com.example.underthesea_aos.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.ScrollView
import android.widget.TextView
import com.example.underthesea_aos.R

//계획 열람을 위한 캘린더
class Main2Activity : AppCompatActivity() {
    lateinit var calendarView: CalendarView
    lateinit var dateTextView: TextView
    lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar2)

        calendarView = findViewById(R.id.calendarView)
        dateTextView = findViewById(R.id.dateTextView)
        scrollView = findViewById(R.id.scroll)
        scrollView.visibility = View.INVISIBLE

        //달력 클릭 이벤트
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            dateTextView.visibility = View.VISIBLE
            dateTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            scrollView.visibility = View.VISIBLE
        }

    }
}