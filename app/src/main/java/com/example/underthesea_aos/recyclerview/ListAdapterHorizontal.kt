package com.example.underthesea_aos.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.underthesea_aos.R

class ListAdapterHorizontal (var list:ArrayList<String>):RecyclerView.Adapter<ListAdapterHorizontal.ListAdapter>(){

    class ListAdapter(val layout : View):RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(LayoutInflater.from(parent.context).inflate(R.layout.activity_recyclerview_hori,parent,false))
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.textImg.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
