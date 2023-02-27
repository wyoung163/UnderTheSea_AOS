package com.example.underthesea_aos.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.databinding.ActivityPlanPreviewBinding

class MyViewHolder(val binding: ActivityPlanPreviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int {
        return datas?.size ?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            =MyViewHolder(ActivityPlanPreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        binding.itemData.text = datas!![position]
    }
}
