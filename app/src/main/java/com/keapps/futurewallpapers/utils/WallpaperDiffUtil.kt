package com.keapps.futurewallpapers.utils

import androidx.recyclerview.widget.DiffUtil
import com.keapps.futurewallpapers.model.WallPaperModel

class WallpaperDiffUtil() :DiffUtil.ItemCallback<WallPaperModel>() {
    override fun areItemsTheSame(oldItem: WallPaperModel, newItem: WallPaperModel): Boolean {
        return oldItem.wallId == newItem.wallId
    }

    override fun areContentsTheSame(oldItem: WallPaperModel, newItem: WallPaperModel): Boolean {
        return oldItem == newItem
    }


}


    /*
    override fun getOldListSize() =oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return when{
          oldList[oldItemPosition].id!=newList[newItemPosition].id ->{
              false
          }
          oldList[oldItemPosition].favorites!=newList[newItemPosition].favorites ->{
              false
          }
          oldList[oldItemPosition].Title!=newList[newItemPosition].Title ->{
              false
          }
        else -> true
      }
    }
}*/