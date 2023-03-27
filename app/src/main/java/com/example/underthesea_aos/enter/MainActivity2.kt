package com.example.underthesea_aos.enter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.underthesea_aos.R
import com.example.underthesea_aos.character.MainActivity
import kotlinx.android.synthetic.main.activity_enter.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter2)

        //로그인 화면으로 NEXT 버튼 클릭 시 이동
        val intent = Intent(this, MainActivity::class.java)
        next.setOnClickListener{startActivity(intent)}
    }
}