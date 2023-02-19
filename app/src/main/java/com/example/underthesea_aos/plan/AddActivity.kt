package com.example.underthesea_aos.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_plan_add.*
import kotlinx.android.synthetic.main.activity_plan_recyclerview.*
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.recyclerview.HorizontalItemDecorator
import com.example.underthesea_aos.recyclerview.VeritcalItemDecorator
import java.text.SimpleDateFormat
import java.util.Date

class AddActivity : AppCompatActivity() {
    lateinit var planAdapter: PlanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_add)

        showDate()
        planadd()

    }

    fun showDate(){
        val date = Date()
        val formatType = SimpleDateFormat("yyyy-MM-dd")
        dateTextView.text = formatType.format(date)
    }

    fun planadd(){
        planAdapter = PlanAdapter(this)

        imageview_plan_recycler.adapter = planAdapter
        imageview_plan_recycler.addItemDecoration(VeritcalItemDecorator(20))
        imageview_plan_recycler.addItemDecoration(HorizontalItemDecorator(10))
    }
}