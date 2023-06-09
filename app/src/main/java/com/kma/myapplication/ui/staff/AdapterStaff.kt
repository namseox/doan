package com.kma.myapplication.ui.staff

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.databinding.ItemStaffBinding

class AdapterStaff(var onCLick: onCLick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<StaffItem>()
    fun getData(isUpdate: Boolean, listStaff: List<StaffItem>) {
        if (isUpdate) {
            data.add(0, listStaff[0])
            Log.d("TAG", "getData: 1")
            notifyItemChanged(0)
        } else {
            data.clear()
            data.addAll(listStaff)
            Log.d("TAG", "getData: 2")
//            notifyDataSetChanged()
            notifyDataSetChanged()
        }

    }

    fun updateStaff(item: StaffItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        data[position]=item
//        notifyItemChanged(position)
        notifyDataSetChanged()
    }

    fun deleteStaff(item: StaffItem) {
        var position = data.indexOf(item)
        data.remove(item)
//        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemStaffBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position], holder.binding,1)
            }
            holder.binding.clStaff.setOnClickListener {
                onCLick.clickItem(data[position], holder.binding,1)
            }
        }
    }

    inner class ViewHolder(val binding: ItemStaffBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.staffModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLick {
    fun click3Dot(staff: StaffItem, binding: ItemStaffBinding,year_id: Int)
    fun clickItem(staff: StaffItem, binding: ItemStaffBinding,year_id:Int)
}