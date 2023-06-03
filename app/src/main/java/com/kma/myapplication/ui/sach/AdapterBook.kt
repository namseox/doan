package com.kma.myapplication.ui.sach

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.databinding.ItemBookBinding

class AdapterBook(var onCLick: onCLickBook) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListBookItem>()
    fun getData(listBook: List<ListBookItem>) {
        data.clear()
        data.addAll(listBook)
        notifyDataSetChanged()
    }

    fun updateBook(item :ListBookItem) {
        var position = -1
        var j = 0
        for (i in data){
            if(i.id==item.id){
                position = j
                break
            }
            j++
        }
        Log.d("TAG", "updateBook000: "+position)
        data[position]=item
        notifyItemChanged(position)
//        notifyDataSetChanged()
    }

    fun deleteBook(item: Book) {
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

    fun creatBook(item:ListBookItem) {
        data.add(0,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterBook.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position].id, holder.binding)
            }
            holder.binding.llClickItem.setOnClickListener {
                onCLick.clickItem(data[position].id, holder.binding)
            }
        }
    }

    inner class ViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.bookModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLickBook {
    fun click3Dot(idBook: Int, binding: ItemBookBinding)
    fun clickItem(idBook: Int, binding: ItemBookBinding)
}
