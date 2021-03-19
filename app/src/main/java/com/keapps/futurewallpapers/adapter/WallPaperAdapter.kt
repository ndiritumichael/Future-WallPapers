package com.keapps.futurewallpapers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keapps.futurewallpapers.databinding.WallpapersAdapterBinding
import com.keapps.futurewallpapers.model.WallPaperModel

class WallPaperAdapter(var wallpaperList: List<WallPaperModel>,
private val listener : OnClickPicListener) :
    RecyclerView.Adapter<WallPaperAdapter.WallPaperViewHolder>() {

    inner class WallPaperViewHolder(val binding: WallpapersAdapterBinding) :RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        fun showFav(){
            binding.favoriteTicked.visibility = View.VISIBLE
            binding.favoriteUnticked.visibility = View.GONE
        }
        fun showNotFav(){
            binding.favoriteUnticked.visibility= View.VISIBLE
            binding.favoriteTicked.visibility = View.GONE
        }
        
        init {
            binding.apply {
                root.setOnClickListener(this@WallPaperViewHolder)

            }
        }

        override fun onClick(v: View?) {
val position = adapterPosition
            if (position!= RecyclerView.NO_POSITION){
listener.onItemClicked(wallpaperList[position])
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallPaperViewHolder {

        return WallPaperViewHolder(WallpapersAdapterBinding
                .inflate(LayoutInflater.from(parent.context),
                        parent,
                         false))

    }

    override fun onBindViewHolder(holder: WallPaperViewHolder, position: Int) {

      holder.apply {
          binding.wallpaperTitle.text = wallpaperList[position].Title
          if (wallpaperList[position].favorites){
            showFav()
          }else{
            showNotFav()
          }
          binding.wallpaperid.load(
                  wallpaperList[position].lowHd
          )
      }
    }



    override fun getItemCount() =wallpaperList.size
    fun updateData(list: List<WallPaperModel>?) {
        if (list != null) {
            wallpaperList = list
        }
        notifyDataSetChanged()

    }
    interface OnClickPicListener {
        fun onItemClicked(wallpaper : WallPaperModel)

    }
}