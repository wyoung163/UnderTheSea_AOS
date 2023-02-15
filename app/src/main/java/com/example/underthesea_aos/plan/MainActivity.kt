package com.example.underthesea_aos.plan

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.underthesea_aos.R
import com.example.underthesea_aos.databinding.ActivityAddBinding
import com.example.underthesea_aos.databinding.ActivityMainBinding
import com.example.underthesea_aos.databinding.ActivityPlanMainBinding
import kotlinx.android.synthetic.main.activity_plan_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityPlanMainBinding
    private var datas : MutableList<String>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityPlanMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            it.data!!.getStringExtra("result")?.let{
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }

        binding.mainFab.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }
        
        datas = savedInstanceState?.let{
            it.getStringArrayList("datas")?.toMutableList()
        }?: let{
            mutableListOf<String>()
        }
        
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this,LinearLayoutManager.VERTICAL)
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("datas",ArrayList(datas))
    }
}
