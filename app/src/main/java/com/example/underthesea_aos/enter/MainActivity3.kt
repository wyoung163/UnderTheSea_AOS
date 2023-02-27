package com.example.underthesea_aos.enter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_enter3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter3)

        //get started 버튼 클릭 시 소개 화면으로 이동
        get_started.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}