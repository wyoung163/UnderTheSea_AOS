package com.example.underthesea_aos.plan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.underthesea_aos.R
import kotlinx.android.synthetic.main.activity_recyclerview_hori.view.*

class PlanAdapter(private val context: Context) : RecyclerView.Adapter<PlanAdapter.ViewHolder>(){
    var dataSet = mutableListOf<RecommendationData>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_plan_recyclerview, viewGroup, false)

        return ViewHolder(view) //view 객체는 한개의 사진이 디자인 된 레이아웃
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
        //onBindViewHolder는 스크롤을 해서 데이터 바인딩이 새롭게 필요할 때 마다 호출된다.
        //Get element from your dataset at this position and replace the
        //contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rec : ImageView
        private val rImage : ImageView = itemView.findViewById(R.id.imageview_plan_recycler)
        init {
            rec = view.findViewById<ImageView>(R.id.imageview_plan_recycler)
        }
        fun bind(item: RecommendationData){
            Glide.with(itemView).load(item.img).into(rImage)
        }
    }
}