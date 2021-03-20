package com.keapps.futurewallpapers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import com.keapps.futurewallpapers.databinding.WallpapersAdapterBinding
import com.keapps.futurewallpapers.model.WallPaperModel

class WallPaperAdapter(var wallpaperList: List<WallPaperModel>,
private val listener : OnClickPicListener,val favListener : (WallPaperModel,Boolean) -> Unit) :
    RecyclerView.Adapter<WallPaperAdapter.WallPaperViewHolder>() {



    inner class WallPaperViewHolder(val binding: WallpapersAdapterBinding) :RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        fun bind(wallpaper: WallPaperModel,favListener: (WallPaperModel,Boolean) -> Unit){
            binding.favoriteTicked.setOnClickListener{
                favListener(wallpaper,false)
            }
            binding.favoriteUnticked.setOnClickListener {
                favListener(wallpaper,true)
            }

        }

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
        holder.bind(wallpaperList[position],favListener)

      holder.apply {
          binding.wallpaperTitle.text = wallpaperList[position].Title
          if (wallpaperList[position].favorites){
            showFav()
          }else{
            showNotFav()
          }


         binding.wallpaperid.load(
                  wallpaperList[position].lowHd
          ){
              crossfade(true)
             listener(
                 onSuccess = {_,_ ->
                 binding.picLoading.visibility = View.GONE
             })
          }

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