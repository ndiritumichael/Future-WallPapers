package com.keapps.futurewallpapers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keapps.futurewallpapers.databinding.ItemCollectionsBinding
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.CategoriesViewer
import com.keapps.futurewallpapers.utils.CollectionsDiffUtil

class CollectionsAdapter():ListAdapter<CategoriesViewer,CollectionsAdapter.CollectionsViewHolder>(CollectionsDiffUtil()) {

    class CollectionsViewHolder(val binding:ItemCollectionsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoriesViewer?) {

            binding.apply {

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        return CollectionsViewHolder(ItemCollectionsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
       holder.bind(getItem(position))
    }
}