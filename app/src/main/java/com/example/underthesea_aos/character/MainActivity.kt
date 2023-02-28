package com.example.underthesea_aos.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_charac.*

class MainActivity : AppCompatActivity() {
    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.turtle1)
        add(R.drawable.seal1)
        add(R.drawable.penguin1)
        add(R.drawable.dolphin1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charac)
        mainInitViewPager2()
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
        //mainViewChangeEvent()
    }
//
//    private fun mainViewChangeEvent(){
//        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
//            override fun onPageSelected(position: Int) {
//
//            }
//        })
//    }
}