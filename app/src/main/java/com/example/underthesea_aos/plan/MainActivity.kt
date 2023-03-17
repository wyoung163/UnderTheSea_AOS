package com.example.underthesea_aos.plan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.databinding.ActivityPlanMainBinding
import kotlinx.android.synthetic.main.activity_plan_main.*
/*
  계획 조회 페이지
 */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlanMainBinding
    private var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent1 = Intent(this, AddActivity::class.java)

        if (intent.hasExtra("date")){
            val strDate = intent.getStringExtra("date").toString()
            date.text = strDate
            intent1.putExtra("date",strDate)
        }

        add_btn.setOnClickListener{
            startActivity(intent1)
        }
    }
}
