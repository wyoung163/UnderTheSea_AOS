package com.example.underthesea_aos.record

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_record3.*

/*
   짧은 기록 view 페이지
 */
class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record3)

        //back 버튼 클릭 시
        val intent1 = Intent(this, com.example.underthesea_aos.calendar.MainActivity::class.java)
        back.setOnClickListener{ startActivity(intent1) }

        //writing 버튼 클릭 -> 기록 작성 페이지로 이동
        val intent2 = Intent(this, MainActivity::class.java)
        writing.setOnClickListener { startActivity(intent2) }

        //캘린더로부터 날짜 받아올 인텐트
        if(intent.hasExtra("date")) {  //date라는 키값을 가진 intent가 정보를 가지고 있다면 실행
            // date라는 id의 textview의 문구를 date라는 키값을 가진 intent의 정보로 변경
            date.text = intent.getStringExtra("date").toString()
        }
    }
}