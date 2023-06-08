package com.kma.myapplication.ui.managerRoomExam

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.RoomItem
import com.kma.myapplication.data.model.ListRoomItem
import com.kma.myapplication.databinding.ItemRoomBinding

class AdapterRoom (var onCLick: onCLickRoom) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListRoomItem>()
    fun getData(listRoom: List<ListRoomItem>) {
        data.clear()
        data.addAll(listRoom)
        notifyDataSetChanged()
    }

    fun updateRoom(item : ListRoomItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        Log.d("TAG", "updateRoom000: "+position)
        data[position]=item
        notifyItemChanged(position)
//        notifyDataSetChanged()
    }

    fun deleteRoom(item: RoomItem) {
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

    fun creatRoom(item: ListRoomItem) {
        data.add(0,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterRoom.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position], holder.binding)
            }
            holder.binding.llClickItem.setOnClickListener {
                onCLick.clickItem(data[position], holder.binding)
            }

        }
    }

    inner class ViewHolder(val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.roomModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLickRoom {
    fun click3Dot(itemListRoom: ListRoomItem, binding: ItemRoomBinding)
    fun clickItem(itemListRoom: ListRoomItem, binding: ItemRoomBinding)
//    fun clickFullItem(idRoom: Int)
}
