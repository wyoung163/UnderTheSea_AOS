package com.example.underthesea_aos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.enter.MainActivity3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hand = Handler()

        //2초 후 소개 화면으로 자동 전환
        hand.postDelayed(Runnable { // TODO Auto-generated method stub
            val i = Intent(this@MainActivity, MainActivity3::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }
}