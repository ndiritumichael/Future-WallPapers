package com.keapps.futurewallpapers.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keapps.futurewallpapers.data.relationships.CategoryinWall
import com.keapps.futurewallpapers.databinding.ItemCollectionsBinding
import com.keapps.futurewallpapers.model.Categories
import com.keapps.futurewallpapers.model.CategoriesViewer
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.utils.CollectionsDiffUtil

class CollectionsAdapter(
    private val listener: OnClickPicListener
):ListAdapter<CategoryinWall,CollectionsAdapter.CollectionsViewHolder>(CollectionsDiffUtil()) {

    inner class CollectionsViewHolder(val binding:ItemCollectionsBinding):RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        init {
            binding.apply {
                root.setOnClickListener(this@CollectionsViewHolder)

            }
        }
        fun bind(item: CategoryinWall?) {

            binding.apply {
                collectionName.text = item!!.category.categories

                if (item.wallpapers.isEmpty()){
                    Log.d("collections","no wallpapers in category")
                } else{
                    collectionNo.text = item.wallpapers.size.toString()
                    binding.collectionImage.load(
                        item.wallpapers[0].lowHd
                    )
                }


            }

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(getItem(position))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        return CollectionsViewHolder(ItemCollectionsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
       holder.bind(getItem(position))
    }
    interface OnClickPicListener {
        fun onItemClicked(categoryinWall: CategoryinWall)

    }
}