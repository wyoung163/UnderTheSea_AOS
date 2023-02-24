package com.example.underthesea_aos.plan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.R

class PreviewAdapterVertical(private val context: Context):
    RecyclerView.Adapter<PreviewAdapterVertical.ViewHolder>()
{
    var vlist = mutableListOf<PlanData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_plan_preview_ver,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = vlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vlist[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val txtTodo : TextView = itemView.findViewById(R.id.plan_preview)

        fun bind(item: PlanData){
            txtTodo.text = item.TodoList
        }
    }
}