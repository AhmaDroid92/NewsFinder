package com.ahmedroid.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedroid.domain.entity.NewsEntity
import com.ahmedroid.ui.databinding.NewsItemBinding

class NewsAdapter(
    private val newsList: List<NewsEntity>,
    private val onItemClickListener: (newsEntity: NewsEntity) -> Unit
) : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListViewHolder(NewsItemBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(newsList[holder.adapterPosition])

    override fun getItemCount(): Int = newsList.size

    inner class ListViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsEntity?) {
            this.binding.root.setOnClickListener {
                onItemClickListener.invoke(newsList[adapterPosition])
            }

            binding.newsItem = item
            binding.executePendingBindings()
        }
    }
}
