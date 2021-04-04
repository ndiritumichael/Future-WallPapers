package com.keapps.futurewallpapers.ui.wallpapers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.keapps.futurewallpapers.R
import com.keapps.futurewallpapers.WallPaperApplication
import com.keapps.futurewallpapers.adapter.WallPaperAdapter
import com.keapps.futurewallpapers.databinding.FragmentWallpapersBinding
import com.keapps.futurewallpapers.model.WallPaperModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WallpapersFragment : Fragment(),WallPaperAdapter.OnClickPicListener {


    private lateinit var wallpapersBinding: FragmentWallpapersBinding
    private lateinit var wallpaperAdapter : WallPaperAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private val wallpapersViewModel : WallpapersViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {



        wallpapersBinding = FragmentWallpapersBinding.inflate(inflater,container,false)


        gridLayoutManager = GridLayoutManager(context,2)
        wallpaperAdapter = WallPaperAdapter(
                this){ wallPaperModel: WallPaperModel, favorite: Boolean ->
            toggleFavorites(wallPaperModel,favorite)

        }
        wallpapersBinding.wallpaperRecycler.adapter = wallpaperAdapter
        wallpapersBinding.wallpaperRecycler.layoutManager = gridLayoutManager
        wallpapersViewModel.wallpaperlist.observe(viewLifecycleOwner){ list->
            if (list.isEmpty()){
                wallpapersBinding.animationView.visibility = View.VISIBLE
                wallpapersBinding.noFavorites.visibility = View.VISIBLE
            }else{
                wallpapersBinding.animationView.visibility = View.GONE
                wallpapersBinding.noFavorites.visibility = View.GONE
            }

            wallpaperAdapter.submitList(list)
        }

        return wallpapersBinding.root
    }



    override fun onItemClicked(wallpaper: WallPaperModel) {
        val action = WallpapersFragmentDirections
                .actionNavigationWallpapersToFullScreenImage(wallpaper.id)
        findNavController().navigate(action)

    }

    private fun toggleFavorites(wallPaperModel: WallPaperModel,isTrue:Boolean){
        wallPaperModel.favorites = isTrue
        wallpapersViewModel.updateData(wallPaperModel)
    }

}