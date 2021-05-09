package com.keapps.futurewallpapers.utils

import androidx.recyclerview.widget.DiffUtil
import com.keapps.futurewallpapers.data.relationships.CategoryinWall
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.CategoriesViewer

class CollectionsDiffUtil():DiffUtil.ItemCallback<CategoryinWall>() {
    override fun areItemsTheSame(oldItem: CategoryinWall, newItem: CategoryinWall): Boolean {
      return oldItem.category.catId == newItem.category.catId
    }

    override fun areContentsTheSame(oldItem: CategoryinWall, newItem: CategoryinWall): Boolean {
        return oldItem == newItem
    }

}