package com.kma.myapplication.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.DashboardClass
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.databinding.ItemArticleBinding
import com.kma.myapplication.databinding.ItemArticleMainBinding
import com.kma.myapplication.databinding.ItemDashboardClassBinding

class AdapterArticle:BaseAdapterMain<ListArticleItem, ItemArticleMainBinding>() {
    fun getValueData(list:List<ListArticleItem>){
        dataUpdate = list
        getData()
        Log.d("TAG", "getValueData: "+dataUpdate)
    }
    override fun creatViewHolder(position: Int) {
        binding.articModel = data[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemArticleMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return super.onCreateViewHolder(parent, viewType)
    }

}