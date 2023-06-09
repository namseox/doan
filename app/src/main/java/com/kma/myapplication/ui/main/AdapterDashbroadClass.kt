package com.kma.myapplication.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.DashboardClass
import com.kma.myapplication.databinding.ItemClassBinding
import com.kma.myapplication.databinding.ItemDashboardClassBinding

class AdapterDashbroadClass:BaseAdapterMain<DashboardClass,ItemDashboardClassBinding>() {
     fun getValueData(list:List<DashboardClass>){
        dataUpdate = list
         getData()
         Log.d("TAG", "getValueData: "+dataUpdate)
    }
    override fun creatViewHolder(position: Int) {
        binding.classModel = data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemDashboardClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return super.onCreateViewHolder(parent, viewType)
    }

}