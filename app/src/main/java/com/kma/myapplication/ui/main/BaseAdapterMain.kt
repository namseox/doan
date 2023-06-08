package com.kma.myapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.ListExamItem
import com.kma.myapplication.databinding.ItemExamBinding
import com.kma.myapplication.ui.managerexam.AdapterExam


abstract class BaseAdapterMain<T, V : ViewDataBinding>() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var binding: V
    var data = mutableListOf<T>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), parent.id, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BaseAdapterMain<*, *>.ViewHolder) {
            holder.bind(position)
        }
    }

    inner class ViewHolder(val binding: V) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            creatViewHolder(position)
        }
    }

    abstract fun creatViewHolder(position : Int)
}
