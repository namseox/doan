package com.kma.myapplication.ui.managerexam

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListExamItem
import com.kma.myapplication.databinding.ItemExamBinding


class AdapterExam(var onCLick: onCLickExam) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListExamItem>()
    fun getData(listExam: List<ListExamItem>) {
        data.clear()
        data.addAll(listExam)
        notifyDataSetChanged()
    }

    fun updateExam(item : ListExamItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        Log.d("TAG", "updateExam000: "+position)
        data[position]=item
        notifyItemChanged(position)
//        notifyDataSetChanged()
    }

    fun deleteExam(item: ExamItem) {
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

    fun creatExam(item: ListExamItem) {
        data.add(0,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterExam.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position].id, holder.binding)
            }
            holder.binding.llClickItem.setOnClickListener {
                onCLick.clickItem(data[position].id, holder.binding)
            }

        }
    }

    inner class ViewHolder(val binding: ItemExamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.examModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLickExam {
    fun click3Dot(id: Int, binding: ItemExamBinding)
    fun clickItem(id: Int, binding: ItemExamBinding)
//    fun clickFullItem(idExam: Int)
}
