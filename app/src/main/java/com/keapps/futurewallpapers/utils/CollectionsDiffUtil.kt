package com.keapps.futurewallpapers.utils

import androidx.recyclerview.widget.DiffUtil
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.CategoriesViewer

class CollectionsDiffUtil():DiffUtil.ItemCallback<CategoriesViewer>() {
    override fun areItemsTheSame(oldItem: CategoriesViewer, newItem: CategoriesViewer): Boolean {
      return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CategoriesViewer, newItem: CategoriesViewer): Boolean {
        return oldItem == newItem
    }

}