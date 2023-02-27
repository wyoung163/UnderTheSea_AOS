package com.example.underthesea_aos.record

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_recoed2.*

/*
    날짜에 해당하는 모든 기록 조회
 */
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recoed2)

        //back 버튼 클릭 시
        val intent1 = Intent(this, com.example.underthesea_aos.calendar.MainActivity::class.java)
        back.setOnClickListener{ startActivity(intent1) }

        //add 버튼 클릭 -> 새 기록 작성 페이지로 이동
        val intent2 = Intent(this, MainActivity::class.java)
        btn_add.setOnClickListener { startActivity(intent2) }
    }
}