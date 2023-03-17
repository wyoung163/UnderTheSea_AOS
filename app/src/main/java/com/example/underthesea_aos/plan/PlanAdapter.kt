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
        return ViewHolder(view) //view 객체는 한개의 사진이 디자인 된 레이아웃
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //onBindViewHolder는 스크롤을 해서 데이터 바인딩이 새롭게 필요할 때 마다 호출된다.
        //Get element from your dataset at this position and replace the
        //contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name : TextView = itemView.findViewById(R.id.name_plan_recycler1)
        private val cont : TextView = itemView.findViewById(R.id.name_plan_recycler1)
        private val page : TextView = itemView.findViewById(R.id.name_plan_recycler1)
        //private val r2Image : ImageView = itemView.findViewById(R.id.imageview_plan_recycler2)
        fun bind(item: RecommendationData){
            name.text = item.name
            cont.text = item.cont
            page.text = item.page
            //Glide.with(itemView).load(item.name).into(r1Image)
            //Glide.with(itemView).load(item.img2).into(r2Image)
        }
    }
}