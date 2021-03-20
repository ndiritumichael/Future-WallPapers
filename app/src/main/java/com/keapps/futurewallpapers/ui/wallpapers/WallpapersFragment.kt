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

class WallpapersFragment : Fragment(),WallPaperAdapter.OnClickPicListener {


    private lateinit var wallpapersBinding: FragmentWallpapersBinding
    private lateinit var wallpaperAdapter : WallPaperAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private val wallpapersViewModel : WallpapersViewModel by viewModels {
        WallViewModelFactory((activity?.application as WallPaperApplication).repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {



        wallpapersBinding = FragmentWallpapersBinding.inflate(inflater,container,false)


        gridLayoutManager = GridLayoutManager(context,2)
        wallpaperAdapter = WallPaperAdapter(emptyList<WallPaperModel>()  ,
                this){ wallPaperModel: WallPaperModel, favorite: Boolean ->
            toggleFavorites(wallPaperModel,favorite)

        }
        wallpapersBinding.wallpaperRecycler.adapter = wallpaperAdapter
        wallpapersBinding.wallpaperRecycler.layoutManager = gridLayoutManager

        return wallpapersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     wallpapersViewModel.wallpaperlist.observe(viewLifecycleOwner){ list->
         if (list.isEmpty()){
             wallpapersBinding.animationView.visibility = View.VISIBLE
             wallpapersBinding.noFavorites.visibility = View.VISIBLE
         }
            wallpaperAdapter.updateData(list)
        }
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