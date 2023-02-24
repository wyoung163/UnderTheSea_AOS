package com.example.underthesea_aos.record

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_record3.*

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record3)

        //캘린더로부터 날짜 받아올 인텐트
        if(intent.hasExtra("date")) {  //date라는 키값을 가진 intent가 정보를 가지고 있다면 실행
            // date라는 id의 textview의 문구를 date라는 키값을 가진 intent의 정보로 변경
            date.text = intent.getStringExtra("date").toString()
        }

    }
}