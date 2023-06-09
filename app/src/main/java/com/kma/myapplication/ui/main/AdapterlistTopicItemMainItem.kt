package com.kma.myapplication.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.DashboardClass
import com.kma.myapplication.data.model.TopicItemMainItem
import com.kma.myapplication.databinding.ItemDashboardClassBinding
import com.kma.myapplication.databinding.ItemTopicItemMainItemBinding

class AdapterlistTopicItemMainItem :BaseAdapterMain<TopicItemMainItem, ItemTopicItemMainItemBinding>() {
    fun getValueData(list:List<TopicItemMainItem>){
        dataUpdate = list
        getData()
        Log.d("TAG", "getValueData: "+dataUpdate)
    }
    override fun creatViewHolder(position: Int) {
        binding.classModel = data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemTopicItemMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return super.onCreateViewHolder(parent, viewType)
    }

}