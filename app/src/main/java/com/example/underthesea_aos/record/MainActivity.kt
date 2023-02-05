package com.example.underthesea_aos.record

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.underthesea_aos.R

class MainActivity : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        spinner = findViewById(R.id.spinner)
        result = findViewById(R.id.txt)
        var data = listOf("aaa", "bbb", "ccc");
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        spinner.adapter = adapter

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //position은 선택한 아이템의 위치를 넘겨주는 인자입니다.
                result.text = data.get(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}