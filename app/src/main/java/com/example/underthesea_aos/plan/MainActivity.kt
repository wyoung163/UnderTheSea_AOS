package com.example.underthesea_aos.plan

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import com.example.underthesea_aos.recyclerview.HorizontalItemDecorator
import com.example.underthesea_aos.recyclerview.VeritcalItemDecorator
import kotlinx.android.synthetic.main.activity_plan_preview.*

class MainActivity : AppCompatActivity(){
    lateinit var planAdapter: PreviewAdapterVertical
    private val vlist = mutableListOf<PlanData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_preview)

        initRecycler()
    } 

    private fun initRecycler(){
        planAdapter = PreviewAdapterVertical(this)
        id_todo.adapter = planAdapter
        id_todo.addItemDecoration(VeritcalItemDecorator(20))
        id_todo.addItemDecoration(HorizontalItemDecorator(10))

        vlist.apply{
            add(PlanData(TodoList = "오늘의 할일"))
            add(PlanData(TodoList = "오늘의 할일"))
            add(PlanData(TodoList = "오늘의 할일"))

            planAdapter.vlist = vlist
            planAdapter.notifyDataSetChanged()
        }
    }
}