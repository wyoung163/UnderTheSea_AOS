package com.example.underthesea_aos.recyclerview

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_recyclerview.*

class MainActivity : AppCompatActivity(){
    lateinit var profileAdapter: ListAdapterHorizontal
    val datas = mutableListOf<ProfileData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        initRecycler()
    }

    private fun initRecycler(){
        profileAdapter = ListAdapterHorizontal(this)

        rv_profile.adapter = profileAdapter
        rv_profile.addItemDecoration(VeritcalItemDecorator(20))
        rv_profile.addItemDecoration(HorizontalItemDecorator(10))

        datas.apply{
            add(ProfileData(img = R.drawable.profile1, name = "a", age = 24))
            add(ProfileData(img = R.drawable.profile2, name = "b", age = 24))
            add(ProfileData(img = R.drawable.profile3, name = "c", age = 24))
            add(ProfileData(img = R.drawable.profile4, name = "d", age = 24))
            add(ProfileData(img = R.drawable.profile5, name = "e", age = 24))
            add(ProfileData(img = R.drawable.profile6, name = "f", age = 24))
            add(ProfileData(img = R.drawable.profile7, name = "g", age = 24))
            add(ProfileData(img = R.drawable.profile8, name = "h", age = 24))

            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()
        }
    }
}
