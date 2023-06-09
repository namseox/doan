package com.kma.myapplication.ui.managerSubject

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListExamItem
import com.kma.myapplication.data.model.ListSubjectItem
import com.kma.myapplication.data.model.SubjectItem
import com.kma.myapplication.databinding.ItemExamBinding
import com.kma.myapplication.databinding.ItemSubjectBinding
import com.kma.myapplication.ui.managerexam.AdapterExam

class AdapterSubject (var onCLick: onCLickSubject) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListSubjectItem>()
    fun getData(listSubject: List<ListSubjectItem>) {
        data.clear()
        data.addAll(listSubject)
        notifyDataSetChanged()
    }

    fun updateSubject(item : ListSubjectItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        Log.d("TAG", "updateSubject000: "+position)
        data[position]=item
        notifyItemChanged(position)
//        notifyDataSetChanged()
    }

    fun deleteSubject(item: SubjectItem) {
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

    fun creatSubject(item: ListSubjectItem) {
        data.add(0,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterSubject.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position].id, holder.binding)
            }
            holder.binding.llClickItem.setOnClickListener {
                onCLick.clickItem(data[position].id, holder.binding)
            }

        }
    }

    inner class ViewHolder(val binding: ItemSubjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.subjectModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLickSubject {
    fun click3Dot(id: Int, binding: ItemSubjectBinding)
    fun clickItem(id: Int, binding: ItemSubjectBinding)
//    fun clickFullItem(idSubject: Int)
}
