package com.example.underthesea_aos.plan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.underthesea_aos.R

class PlanAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<PlanAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rec : ImageView

        init {
            rec = view.findViewById(R.id.imageview_plan_recycler)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_recyclerview, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
        viewHolder.rec.image = dataSet[position]
    }

    override fun getItemCount() = dataSet.size
}