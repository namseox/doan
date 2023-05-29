package com.kma.myapplication.ui.sach

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.databinding.ItemBookBinding
import com.kma.myapplication.databinding.ItemStaffBinding
import com.kma.myapplication.ui.staff.AdapterStaff

class AdapterBook(var onCLick: onCLickBook) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListBookItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterBook.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position], holder.binding)
            }
            holder.binding.clStaff.setOnClickListener {
                onCLick.clickItem(data[position], holder.binding)
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
    fun click3Dot(book: ListBookItem, binding: ItemBookBinding)
    fun clickItem(book: Book, binding: ItemBookBinding)
}
