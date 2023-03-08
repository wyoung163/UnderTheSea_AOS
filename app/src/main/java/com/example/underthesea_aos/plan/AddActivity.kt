package com.example.underthesea_aos.plan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_plan_add.*
import kotlinx.android.synthetic.main.activity_plan_recyclerview.*
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.databinding.ActivityPlanAddBinding
import com.example.underthesea_aos.databinding.ActivityPlanPreviewRecyclerviewBinding
import com.example.underthesea_aos.recyclerview.HorizontalItemDecorator
import com.example.underthesea_aos.recyclerview.VeritcalItemDecorator
import java.text.SimpleDateFormat
import java.util.Date

/*
     계획 상세 페이지
 */

class AddActivity : AppCompatActivity() {
    lateinit var planAdapter: PlanAdapter
    private val dataSet = mutableListOf<RecommendationData>()
    lateinit var binding : ActivityPlanAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_add)
        showDate()
        initRecycler()
        back()
        cancel()
    }

    private fun showDate(){
        val date = Date()
        val formatType = SimpleDateFormat("yyyy-MM-dd")
        dateTextView.text = formatType.format(date)
    }

    private fun initRecycler(){
        planAdapter = PlanAdapter(this)

        recommendation.adapter = planAdapter
        recommendation.addItemDecoration(VeritcalItemDecorator(20))
        recommendation.addItemDecoration(HorizontalItemDecorator(10))

        dataSet.apply {
            add(RecommendationData(img1 = R.drawable.rectangle1, img2 = R.drawable.rectangle1))
            add(RecommendationData(img1 = R.drawable.rectangle1, img2 = R.drawable.rectangle1))
            add(RecommendationData(img1 = R.drawable.rectangle1, img2 = R.drawable.rectangle1))
        }

        planAdapter.dataSet = dataSet
        planAdapter.notifyDataSetChanged()
    }

    //preview 페이지 돌아가기
    private fun back(){
        binding.backBtn.setOnClickListener{
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }
    }

    //cancle
    private fun cancel(){
        binding.cancelButton.setOnClickListener{
           val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }
    }

    //save

}