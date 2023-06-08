package com.kma.myapplication.ui.managerMark

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.MarkItem
import com.kma.myapplication.data.model.ListMarkItem
import com.kma.myapplication.databinding.ItemMarkBinding
import com.kma.myapplication.ui.managerMark.AdapterMark

class AdapterMark(var onCLick: onCLickMark) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListMarkItem>()
    fun getData(listMark: List<ListMarkItem>) {
        data.clear()
        data.addAll(listMark)
        notifyDataSetChanged()
    }

    fun updateMark(item : ListMarkItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        Log.d("TAG", "updateMark000: "+position)
        data[position]=item
        notifyItemChanged(position)
//        notifyDataSetChanged()
    }

    fun deleteMark(item: MarkItem) {
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

    fun creatMark(item: ListMarkItem) {
        data.add(0,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterMark.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position].id, holder.binding)
            }
            holder.binding.llClickItem.setOnClickListener {
                onCLick.clickItem(data[position].id, holder.binding)
            }

        }
    }

    inner class ViewHolder(val binding: ItemMarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.markModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLickMark {
    fun click3Dot(id: Int, binding: ItemMarkBinding)
    fun clickItem(id: Int, binding: ItemMarkBinding)
//    fun clickFullItem(idMark: Int)
}
