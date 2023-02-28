package com.example.underthesea_aos.character

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.underthesea_aos.R
import com.example.underthesea_aos.calendar.MainActivity
import kotlinx.android.synthetic.main.activity_charac.*

class MainActivity : AppCompatActivity() {
    var characNum = 0
    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.turtle1)
        add(R.drawable.seal1)
        add(R.drawable.penguin1)
        add(R.drawable.dolphin1)
    }
    lateinit var intent1: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charac)
        mainInitViewPager2()
        //캘린더로 화면 전환할 intent
        intent1 = Intent(this, MainActivity::class.java)
    }

    private fun mainInitViewPager2(){
        viewPager.apply {
            clipToPadding= false
            clipChildren= false
            offscreenPageLimit = 1
            adapter = ViewPager2Adepter(this@MainActivity, imageList)
        }
        viewPager.setPageTransformer(MarginPageTransformer(0))
        viewPager.setPadding(100,0,100,0)
        mainViewChangeEvent()
    }

    private fun mainViewChangeEvent(){
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                btn_confirm.setOnClickListener{
                    characNum = position;
                    //Log.d("num", characNum.toString())
                    startActivity(intent1)
                }
            }
        })
    }
}