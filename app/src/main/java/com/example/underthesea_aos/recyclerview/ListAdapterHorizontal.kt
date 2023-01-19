package com.example.underthesea_aos.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.underthesea_aos.R

class ListAdapterHorizontal(private val context: Context):
    RecyclerView.Adapter<ListAdapterHorizontal.ViewHolder>()
{
    var datas = mutableListOf<ProfileData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.activity_recyclerview_hori,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val txtName : TextView = itemView.findViewById(R.id.text_name)
        private val txtAge : TextView = itemView.findViewById(R.id.text_age)
        private val imgProfile : ImageView = itemView.findViewById(R.id.img_photo)

        fun bind(item: ProfileData){
            txtName.text = item.name
            txtAge.text = item.age.toString()
            Glide.with(itemView).load(item.img).into(imgProfile)
        }
    }
}
