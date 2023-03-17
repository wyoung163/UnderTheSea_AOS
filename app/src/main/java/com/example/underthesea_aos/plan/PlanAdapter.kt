package com.example.underthesea_aos.plan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.R

class PlanAdapter(private val context: Context) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>()
{
    var dataSet = mutableListOf<RecommendationData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_plan_recyclerview, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name : TextView = itemView.findViewById(R.id.res_name)
        private val cont : TextView = itemView.findViewById(R.id.res_content)
        private val page : TextView = itemView.findViewById(R.id.res_url)

        fun bind(item: RecommendationData){
            name.text = item.name
            cont.text = item.cont
            page.text = item.page
        }
    }
}