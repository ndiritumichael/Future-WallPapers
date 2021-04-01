package com.keapps.futurewallpapers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import com.keapps.futurewallpapers.databinding.WallpapersAdapterBinding
import com.keapps.futurewallpapers.model.WallPaperModel
import com.keapps.futurewallpapers.utils.WallpaperDiffUtil

class WallPaperAdapter(
       private val listener: OnClickPicListener,
    private val favListener: (WallPaperModel, Boolean) -> Unit
) :
    ListAdapter<WallPaperModel, WallPaperAdapter.WallPaperViewHolder>(WallpaperDiffUtil()) {


    inner class WallPaperViewHolder(val binding: WallpapersAdapterBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        fun bind(wallpaper: WallPaperModel, favListener: (WallPaperModel, Boolean) -> Unit) {
            binding.favoriteTicked.setOnClickListener {
                favListener(wallpaper, false)
                showNotFav()
            }
            binding.favoriteUnticked.setOnClickListener {
                favListener(wallpaper, true)
                showFav()
            }

        }

        fun showFav() {
            binding.favoriteTicked.visibility = View.VISIBLE
            binding.favoriteUnticked.visibility = View.GONE
        }

        fun showNotFav() {
            binding.favoriteUnticked.visibility = View.VISIBLE
            binding.favoriteTicked.visibility = View.GONE
        }

        init {
            binding.apply {
                root.setOnClickListener(this@WallPaperViewHolder)

            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(getItem(position))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallPaperViewHolder {

        return WallPaperViewHolder(
            WallpapersAdapterBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )

    }

    override fun onBindViewHolder(holder: WallPaperViewHolder, position: Int) {
        holder.bind(getItem(position), favListener)

        holder.apply {
            binding.wallpaperTitle.text = getItem(position).Title
            if (getItem(position).favorites) {
                showFav()
            } else {
                showNotFav()
            }


            binding.wallpaperid.load(
                getItem(position).lowHd

            ) {
                crossfade(true)
                listener(
                    onSuccess = { _, _ ->
                        binding.picLoading.visibility = View.GONE
                    })
            }

        }
    }


   //override fun getItemCount() = wallpaperList.size
  /*  fun updateData(newList: List<WallPaperModel>) {


        val diffUtil = WallpaperDiffUtil(newList, wallpaperList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        wallpaperList = newList
        diffResults.dispatchUpdatesTo(this)
        //notifyDataSetChanged()

    }*/
  /*  fun updateData1(list: List<WallPaperModel>?) {
        if (list != null) {
            wallpaperList = list
        }
        notifyDataSetChanged()

    }*/

    interface OnClickPicListener {
        fun onItemClicked(wallpaper: WallPaperModel)

    }
}