package com.kma.myapplication.ui.managerClass

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.ClassItem
import com.kma.myapplication.data.model.ListClassItem
import com.kma.myapplication.databinding.ItemClassBinding



class AdapterClass (var onCLick: onCLickClass) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListClassItem>()
    fun getData(listClass: List<ListClassItem>) {
        data.clear()
        data.addAll(listClass)
        notifyDataSetChanged()
    }

    fun updateClass(item : ListClassItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        Log.d("TAG", "updateClass000: "+position)
        data[position]=item
        notifyItemChanged(position)
//        notifyDataSetChanged()
    }

    fun deleteClass(item: ClassItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        data.removeAt(position)
        notifyItemRemoved(position)
//        notifyDataSetChanged()
    }

    fun creatClass(item: ListClassItem) {
        data.add(0,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterClass.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position].id, holder.binding)
            }
            holder.binding.llClickItem.setOnClickListener {
                onCLick.clickItem(data[position].id, holder.binding)
            }

        }
    }

    inner class ViewHolder(val binding: ItemClassBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.classModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLickClass {
    fun click3Dot(id: Int, binding: ItemClassBinding)
    fun clickItem(id: Int, binding: ItemClassBinding)
//    fun clickFullItem(idClass: Int)
}
