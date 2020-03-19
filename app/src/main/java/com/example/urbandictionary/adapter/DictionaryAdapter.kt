package com.example.urbandictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionary.databinding.DictionaryViewBinding
import com.example.urbandictionary.model.Search

/**
 * @author harishrevuri created on March 18th 2020
 */
class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    private val items: MutableList<Search> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DictionaryViewBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateAdapter(searchList: List<Search>?) {
        items.clear()
        searchList?.let {
            items.addAll(searchList)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: DictionaryViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}