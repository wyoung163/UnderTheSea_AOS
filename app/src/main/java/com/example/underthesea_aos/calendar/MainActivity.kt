package com.example.underthesea_aos.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener

class MainActivity : AppCompatActivity() {
    lateinit var calendar: MaterialCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar2)

        calendar = findViewById(R.id.calendarView)
        calendar.setSelectedDate(CalendarDay.today())

        calendar.addDecorator(TodayDecorator(this@MainActivity))

        calendar.setOnDateChangedListener(object: OnDateSelectedListener {
            override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
                calendar.removeDecorators()
                calendar.addDecorator(TodayDecorator(this@MainActivity))
                calendar.addDecorator(EventDecorator(this@MainActivity, date))
            }
        })
    }
}