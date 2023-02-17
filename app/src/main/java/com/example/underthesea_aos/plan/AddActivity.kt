package com.example.underthesea_aos.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.Date

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        showDate()

    }

    fun showDate(){
        val date = Date()
        val formatType = SimpleDateFormat("yyyy-MM-dd")
        dateTextView.text = formatType.format(date)
    }
}