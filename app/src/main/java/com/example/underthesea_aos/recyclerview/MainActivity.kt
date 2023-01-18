package com.example.underthesea_aos.recyclerview

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.R

class MainActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        var list = arrayListOf("test1","test2","test3","test4")
        var manager = LinerLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        var adapter01 = ListAdapterHorizontal(list)

        var RecyclerView = recyclerHorizon.apply{
            adapter = adapter01
            layoutManager = manager
        }
    }
}