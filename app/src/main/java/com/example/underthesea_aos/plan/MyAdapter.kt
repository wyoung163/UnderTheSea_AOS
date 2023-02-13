package com.example.underthesea_aos.plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class CustomViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val itemIcon = v.item_icon
    val itemData = v.item_data
}

class Data(val profile: Int, val todo: String)

class MyAdapter : RecyclerView.Adapter<CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemIcon.setImageResource(DataList[position].profile)
        holder.itemData.text = DataList[position].todo
    }

    override fun getItemCount(): Int {
        return DataList.size
    }

}