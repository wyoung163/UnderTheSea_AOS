package com.example.underthesea_aos.enter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_enter.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)

        //소개화면2로 NEXT 버튼 클릭 시 이동
        val intent = Intent(this, MainActivity2::class.java)
        next.setOnClickListener{startActivity(intent)}
    }
}