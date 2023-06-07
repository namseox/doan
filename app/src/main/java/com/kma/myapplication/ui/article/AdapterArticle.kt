package com.kma.myapplication.ui.article

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.databinding.ItemArticleBinding

import com.kma.myapplication.ui.book.AdapterBook

class AdapterArticle (var onCLick: onCLickArticle) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = mutableListOf<ListArticleItem>()
    fun getData(listBook: List<ListArticleItem>) {
        data.clear()
        data.addAll(listBook)
        notifyDataSetChanged()
    }

    fun updateBook(item : ListArticleItem) {
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

    fun deleteArticle(item: ArticleItem) {
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

    fun creatBook(item: ListArticleItem) {
        data.add(0,item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AdapterArticle.ViewHolder) {
            holder.bind(position)
            holder.binding.iv3dot.setOnClickListener {
                onCLick.click3Dot(data[position].id, holder.binding)
            }
            holder.binding.llClickItem.setOnClickListener {
                onCLick.clickItem(data[position].id, holder.binding)
            }

        }
    }

    inner class ViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.articModel = data[position]
            binding.tvStt.text = (position + 1).toString()
        }
    }
}

interface onCLickArticle {
    fun click3Dot(id: Int, binding: ItemArticleBinding)
    fun clickItem(id: Int, binding: ItemArticleBinding)
//    fun clickFullItem(idBook: Int)
}
