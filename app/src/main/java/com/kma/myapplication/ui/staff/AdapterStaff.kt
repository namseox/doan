package com.kma.myapplication.ui.staff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.databinding.ItemStaffBinding

class AdapterStaff(var onCLick: onCLick): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<StaffItem>()
    fun getData(listStaff :List<StaffItem>){
        data.addAll(listStaff)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemStaffBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener{
                onCLick.click3Dot(data[position],holder.binding)
            }
            holder.binding.clStaff.setOnClickListener{
                onCLick.clickItem(data[position],holder.binding)
            }
        }
    }
    inner class ViewHolder(val binding: ItemStaffBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.staffModel = data[position]
            binding.tvStt.text = (position+1).toString()
        }
    }
}
interface onCLick{
    fun click3Dot(staff: StaffItem, binding : ItemStaffBinding)
    fun clickItem(staff: StaffItem, binding : ItemStaffBinding)
}