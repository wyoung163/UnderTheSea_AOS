package com.example.underthesea_aos.plan

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_plan_main.*

var DataList = ArrayList<Data>()

class MainActivity : AppCompatActivity() {

    val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        val resultData = it.data?.getStringExtra("result")
        println("result data is : "+ resultData.toString())
        DataList += Data(R.drawable.todo, resultData.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_main)
        main_recyclerView.layoutManager = LinearLayoutManager(this)
        main_recyclerView.adapter = MyAdapter()

        main_fab.setOnClickListener(){
            val intent : Intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }
    }
}
