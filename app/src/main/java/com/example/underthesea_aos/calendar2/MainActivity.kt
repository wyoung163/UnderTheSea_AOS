package com.example.underthesea_aos.calendar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.underthesea_aos.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var calendar: MaterialCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        calendar = findViewById(R.id.calendar)
        calendar.setSelectedDate(CalendarDay.today())

        calendar.addDecorator(TodayDecorator())

        calendar.setOnDateChangedListener(object: OnDateSelectedListener {
            override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
                calendar.addDecorator(EventDecorator(Collections.singleton(date)))
            }
        })
    }
}